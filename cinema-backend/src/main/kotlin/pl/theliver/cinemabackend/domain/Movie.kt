package pl.theliver.cinemabackend.domain

import java.time.LocalDate
import java.util.*

data class Movie(
        val id: String = UUID.randomUUID().toString(),
        val title: String,
        val description: String,
        val genre: String,
        val image: String,
        var rate: Double,
        val ratesNumber: Int,
        val durationTime: Int,
        val seancesIds: List<String>,
        val releaseDate: LocalDate,
        val director: String,
        val ratesIds: List<String>
)