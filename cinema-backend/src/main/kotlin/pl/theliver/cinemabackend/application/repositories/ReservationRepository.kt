package pl.theliver.cinemabackend.application.repositories

import pl.theliver.cinemabackend.domain.Reservation
import pl.theliver.cinemabackend.domain.User

interface ReservationRepository {
    fun saveReservation(reservation: Reservation)
    fun getAllReservations(): List<Reservation>
    fun getReservationById(id: String): Reservation
    fun getReservationByUser(user: User): List<Reservation>
}