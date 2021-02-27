package pl.theliver.cinemabackend.presentation.model

import pl.theliver.cinemabackend.domain.Movie
import pl.theliver.cinemabackend.domain.Rate
import pl.theliver.cinemabackend.domain.Seance
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
        val seances: Collection<Seance>,
        val releaseDate: Date,
        val director: String,
        val rates: Collection<Rate>
) {
//    fun toDomain() = Movie(id, title, description, genre, image, rate, ratesNumber, durationTime, seances, releaseDate, director, rates)
//
//    companion object {
//        fun fromDomain(movie: Movie) = with(movie) { MovieDto(id, title, description, genre, image, rate, ratesNumber, durationTime, seances, releaseDate, director, rates) }
//    }
}