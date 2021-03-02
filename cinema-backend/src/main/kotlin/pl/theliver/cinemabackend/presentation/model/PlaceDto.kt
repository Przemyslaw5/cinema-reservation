package pl.theliver.cinemabackend.presentation.model

import pl.theliver.cinemabackend.application.services.ReservationService
import pl.theliver.cinemabackend.application.services.SeanceService
import pl.theliver.cinemabackend.domain.Place
import java.util.*

data class PlaceDto(
        val id: String = UUID.randomUUID().toString(),
        val number: Int,
        var isReserved: Boolean = false,
//        val seanceId: String,
//        var reservationId: String? = null
) {
    fun toDomain(
            seanceService: SeanceService,
            reservationService: ReservationService
    ) = Place(
            id,
            number,
            isReserved,
            seanceService.getAllSeances().map { it.places }.flatMap { it.toList() }.filter { it.id == id }[0].seanceId,
            reservationService.getAllReservations().map { it.places }.flatMap { it.toList() }.filter { it.id == id }?.let { it[0].reservationId }
    )

    companion object {
        fun fromDomain(place: Place) = with(place) {
            PlaceDto(
                    id,
                    number,
                    isReserved
            )
        }
    }
}