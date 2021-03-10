package pl.theliver.cinemabackend.application.repositories

import pl.theliver.cinemabackend.domain.Reservation
import pl.theliver.cinemabackend.domain.User

interface ReservationRepository {
    fun saveReservation(reservation: Reservation)
    fun getAllReservations(): List<Reservation>
    fun getReservationById(id: String): Reservation
    fun getReservationByUserId(id: String): List<Reservation>
    fun getAllReservationsBySeanceId(id: String): List<Reservation>
    fun deleteById(id: String)

}