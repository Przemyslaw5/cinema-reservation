package pl.theliver.cinemabackend.domain

import java.util.*

data class ScreeningRoom(
        val id: String = UUID.randomUUID().toString(),
        val name: String,
        val placeNumber: Int,
        val placesPlan: MutableList<String>,
        val seancesIds: MutableList<String> = mutableListOf()
)
