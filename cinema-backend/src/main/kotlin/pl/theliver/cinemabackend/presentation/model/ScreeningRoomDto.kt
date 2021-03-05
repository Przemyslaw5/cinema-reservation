package pl.theliver.cinemabackend.presentation.model

import pl.theliver.cinemabackend.domain.ScreeningRoom

data class ScreeningRoomDto(
        val id: String,
        val name: String,
        val placeNumber: Int,
        val placesPlan: MutableList<String>,
) {
    companion object {
        fun fromDomain(screeningRoom: ScreeningRoom) = with(screeningRoom) {
            ScreeningRoomDto(
                    id,
                    name,
                    placeNumber,
                    placesPlan
            )
        }
    }
}