package pl.theliver.cinemabackend.presentation.model

import pl.theliver.cinemabackend.domain.Movie
import pl.theliver.cinemabackend.domain.Rate
import pl.theliver.cinemabackend.domain.Seance
import java.time.LocalDate
import java.util.*

data class MovieDto(
        val id: String = UUID.randomUUID().toString(),
        val title: String,
        val description: String,
        val genre: String,
        val image: String,
        var rate: Double,
        val ratesNumber: Int,
        val durationTime: Int,
        val seances: List<Seance>,
        val releaseDate: LocalDate,
        val director: String,
        val rates: List<Rate>
) {
//    fun toDomain() = Movie(id, title, description, genre, image, rate, ratesNumber, durationTime, seances, releaseDate, director, rates)
//
//    companion object {
//        fun fromDomain(movie: Movie) = with(movie) { MovieDto(id, title, description, genre, image, rate, ratesNumber, durationTime, seances, releaseDate, director, rates) }
//    }
}