package pl.theliver.cinemabackend.domain

import java.util.*

data class ScreeningRoom(
        val id: String = UUID.randomUUID().toString(),
        val name: String,
        val placeNumber: Int,
        val placesPlan: List<String>,
        val seancesIds: List<String>
)
