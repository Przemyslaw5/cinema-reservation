package pl.theliver.cinemabackend.domain

import java.util.*

data class Movie(
        val id: String,
        val title: String,
        val description: String,
        val genre: String,
        val image: String,
        var rate: Double,
        val ratesNumber: Int,
        val durationTime: Int,
        val seancesIds: Collection<String>,
        val releaseDate: Date,
        val director: String,
        val ratesIds: Collection<String>
)