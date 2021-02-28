package pl.theliver.cinemabackend.domain

import java.util.*

data class Reservation(
        val id: String = UUID.randomUUID().toString(),
        var user: User,
        var places: List<Place>,
        var secretWord: String
)
