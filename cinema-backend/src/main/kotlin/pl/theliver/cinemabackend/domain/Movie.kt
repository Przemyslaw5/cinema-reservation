package pl.theliver.cinemabackend.domain

import java.time.LocalDate
import java.util.*

data class Movie(
        val id: String = UUID.randomUUID().toString(),
*        val title: String,
        val description: String,
*        val genre: String,
*        val image: String,
*        var rate: Double = 0.0,
        var ratesNumber: Int = 0,
*        val durationTime: Int,
        val seancesIds: MutableList<String> = mutableListOf(),
        val releaseDate: LocalDate,
        val director: String,
        val ratesIds: MutableList<String> = mutableListOf()
)