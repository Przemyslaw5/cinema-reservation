package pl.theliver.cinemabackend.presentation.model

import pl.theliver.cinemabackend.domain.Place

data class PlaceDto(
        val id: String,
        val number: Int,
        var isReserved: Boolean = false,
) {
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