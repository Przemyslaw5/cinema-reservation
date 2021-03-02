package pl.theliver.cinemabackend.presentation.model

import pl.theliver.cinemabackend.application.services.SeanceService
import pl.theliver.cinemabackend.domain.ScreeningRoom
import java.util.*

data class ScreeningRoomDto(
        val id: String = UUID.randomUUID().toString(),
        val name: String,
        val placeNumber: Int,
        val placesPlan: MutableList<String>,
) {
    fun toDomain(
            seanceService: SeanceService,
    ) = ScreeningRoom(
            id,
            name,
            placeNumber,
            placesPlan,
            seanceService.getAllSeances().filter { it.screeningRoomId == id }.map { it.id }.toMutableList()
    )

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