package pl.theliver.cinemabackend.application.services

import org.springframework.stereotype.Service
import pl.theliver.cinemabackend.application.repositories.MovieRepository
import pl.theliver.cinemabackend.application.repositories.ReservationRepository
import pl.theliver.cinemabackend.application.repositories.ScreeningRoomRepository
import pl.theliver.cinemabackend.domain.Reservation
import pl.theliver.cinemabackend.domain.Seance
import pl.theliver.cinemabackend.domain.User
import javax.transaction.Transactional

@Service
@Transactional
class ReservationService(
        private val reservationRepository: ReservationRepository,
        private val movieRepository: MovieRepository,
        private val screeningRoomRepository: ScreeningRoomRepository
) {

    fun addReservation(reservation: Reservation) = reservationRepository.saveReservation(reservation)

    fun getAllReservations() = reservationRepository.getAllReservations()

    fun getReservationById(id: String) = reservationRepository.getReservationById(id)

    fun getAllReservationFromUser(user: User): Pair<List<Reservation>, Map<String, String>> {
        val map = movieRepository.getAllMovies().map { it.id to it.title }.toMap().toMutableMap()
        map.putAll(screeningRoomRepository.getAllScreeningRooms().map { it.id to it.name })
        return Pair(
                reservationRepository.getReservationByUser(user),
                map
        )
    }
}