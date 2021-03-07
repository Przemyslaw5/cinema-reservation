package pl.theliver.cinemabackend.application.services

import org.springframework.stereotype.Service
import pl.theliver.cinemabackend.application.repositories.ReservationRepository
import pl.theliver.cinemabackend.domain.Reservation
import pl.theliver.cinemabackend.domain.User
import javax.transaction.Transactional

@Service
@Transactional
class ReservationService(
        private val reservationRepository: ReservationRepository
) {

    fun addReservation(reservation: Reservation) = reservationRepository.saveReservation(reservation)

    fun getAllReservations() = reservationRepository.getAllReservations()

    fun getReservationById(id: String) = reservationRepository.getReservationById(id)

//    fun getAllReservationFromUser(user: User) = reservationRepository.getReservationByUser(user)
}