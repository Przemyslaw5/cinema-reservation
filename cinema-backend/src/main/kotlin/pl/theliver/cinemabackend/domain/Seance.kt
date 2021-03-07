package pl.theliver.cinemabackend.domain

import java.time.LocalDateTime
import java.util.*

data class Seance(
        val id: String = UUID.randomUUID().toString(),
        val startDate: LocalDateTime,
        val places: MutableList<Place>,
        val movieId: String,
        val screeningRoomId: String,
        var reservationsIds: MutableList<String> = mutableListOf()
)
