package pl.theliver.cinemabackend.domain

import java.util.*


data class Seance(
        val id: String,
        val startDate: Date,
        val places: Collection<Place>,
        val movieId: String,
        val screeningRoomId: String,
        val durationTime: Int
)
