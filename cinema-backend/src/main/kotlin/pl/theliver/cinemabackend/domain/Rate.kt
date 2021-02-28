package pl.theliver.cinemabackend.domain

import java.util.*

data class Rate(
        val id: String = UUID.randomUUID().toString(),
        val user: User,
        val userRate: Double,
        val movie: Movie
)
