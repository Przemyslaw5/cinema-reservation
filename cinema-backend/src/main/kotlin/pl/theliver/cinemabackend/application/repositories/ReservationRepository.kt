package pl.theliver.cinemabackend.application.repositories

import pl.theliver.cinemabackend.domain.Reservation

interface ReservationRepository {
    fun saveReservation(reservation: Reservation)
    fun getAllReservations(): List<Reservation>
    fun getReservationById(id: String): Reservation
}