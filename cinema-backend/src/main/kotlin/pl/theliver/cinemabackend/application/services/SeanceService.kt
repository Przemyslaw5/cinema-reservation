package pl.theliver.cinemabackend.application.services

import org.springframework.stereotype.Service
import pl.theliver.cinemabackend.application.repositories.*
import pl.theliver.cinemabackend.domain.Place
import pl.theliver.cinemabackend.domain.Seance
import javax.transaction.Transactional

@Service
@Transactional
class SeanceService(
    private val seanceRepository: SeanceRepository,
    private val screeningRoomRepository: ScreeningRoomRepository,
    private val movieRepository: MovieRepository,
    private val placeRepository: PlaceRepository,
    private val reservationRepository: ReservationRepository
) {

    fun addSeance(seance: Seance) = seanceRepository.saveSeance(seance)

    fun getAllSeances() = seanceRepository.getAllSeances()

    fun getSeanceById(id: String) = seanceRepository.getSeanceById(id)

    fun getSeancesFromMovie(id: String) = seanceRepository.getAllSeancesByMovieId(id)

    fun getSeancesByScreeningRoomId(id: String) = seanceRepository.getAllSeancesByScreeningRoomId(id)

    fun deleteSeanceById(id: String) {
        placeRepository.getAllPlacesBySeanceId(id).map { placeRepository.deleteById(it.id) }
        reservationRepository.getAllReservationsBySeanceId(id).map { reservationRepository.deleteById(it.id) }
        seanceRepository.deleteById(id)
    }

    fun getSeancesByMovieIdAndDictForRoom(id: String): Pair<List<Seance>, Map<String, String>> {
        return Pair(
            seanceRepository.getAllSeancesByMovieId(id),
            screeningRoomRepository.getAllScreeningRooms().map { it.id to it.name }.toMap()
        )
    }

    fun isSeanceConflictedWithOthers(seance: Seance): Boolean {
        val seances = seanceRepository.getAllSeancesByScreeningRoomId(seance.screeningRoomId).filter {
            it.startDate.year == seance.startDate.year &&
                    it.startDate.monthValue == seance.startDate.monthValue &&
                    it.startDate.dayOfMonth == seance.startDate.dayOfMonth
        }

        val seanceStartMin = (seance.startDate.hour * 60) + seance.startDate.minute
        val movieDurationTime = movieRepository.getMovieById(seance.movieId).durationTime
        val seanceEndMin = (seance.startDate.hour * 60) + seance.startDate.minute + movieDurationTime

        val seancesConflicted = seances.filter {
            ((seanceStartMin <= ((it.startDate.hour * 60) + it.startDate.minute)) &&
                    (seanceEndMin >= ((it.startDate.hour * 60) + it.startDate.minute))) ||
                    (seanceStartMin <= (((it.startDate.hour * 60) + it.startDate.minute) + movieDurationTime)) &&
                    (seanceEndMin >= (((it.startDate.hour * 60) + it.startDate.minute) + movieDurationTime))
        }.toList()

        return seancesConflicted.isNotEmpty()
    }

    fun prepareNewPlacesForSeance(seance: Seance) {
        val screeningRoom = screeningRoomRepository.getScreeningRoomById(seance.screeningRoomId)

        (0 until screeningRoom.placeNumber).forEach { placeNumberIndex ->
            val place = Place(
                number = placeNumberIndex + 1,
                seanceId = seance.id
            )
            placeRepository.savePlace(place)
            seance.places.add(place)
        }
        seanceRepository.saveSeance(seance)
    }

    fun createNewSeanceIfNotHoursConflict(seance: Seance): Seance? {
        if (isSeanceConflictedWithOthers(seance)) {
            return null
        }
        seanceRepository.saveSeance(seance)
        prepareNewPlacesForSeance(seance)
        return seance
    }
}