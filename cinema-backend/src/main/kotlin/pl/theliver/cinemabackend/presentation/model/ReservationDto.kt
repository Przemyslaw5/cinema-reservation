package pl.theliver.cinemabackend.presentation.model

import pl.theliver.cinemabackend.domain.Reservation

class ReservationDto(
        val id: String,
        val places: List<PlaceDto>,
        var secretWord: String,
) {
    companion object {
        fun fromDomain(reservation: Reservation) = with(reservation) {
            ReservationDto(
                    id,
                    places.map { PlaceDto.fromDomain(it) },
                    secretWord
            )
        }
    }
}