package pl.theliver.cinemabackend.application.services

import org.springframework.stereotype.Service
import pl.theliver.cinemabackend.application.repositories.*
import pl.theliver.cinemabackend.domain.Reservation
import javax.transaction.Transactional

@Service
@Transactional
class ReservationService(
    private val reservationRepository: ReservationRepository,
    private val movieRepository: MovieRepository,
    private val screeningRoomRepository: ScreeningRoomRepository,
    private val placeRepository: PlaceRepository
) {

    fun addReservation(reservation: Reservation) = reservationRepository.saveReservation(reservation)

    fun getAllReservations() = reservationRepository.getAllReservations()

    fun getReservationById(id: String) = reservationRepository.getReservationById(id)

    fun getAllReservationsByUserId(id: String): Triple<List<Reservation>, Map<String, String>, Map<String, String>> {
        return Triple(
            reservationRepository.getReservationByUserId(id),
            movieRepository.getAllMovies().map { it.id to it.title }.toMap(),
            screeningRoomRepository.getAllScreeningRooms().map { it.id to it.name }.toMap()
        )
    }

    fun addNewReservation(reservation: Reservation): Boolean {
        addReservation(reservation)
        reservation.places.forEach { it.reservationId = reservation.id; placeRepository.savePlace(it) }
        return true
    }
}