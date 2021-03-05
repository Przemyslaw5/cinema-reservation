package pl.theliver.cinemabackend.presentation.model

import pl.theliver.cinemabackend.domain.Movie
import java.time.format.DateTimeFormatter

data class MovieDto(
        val id: String,
        val title: String,
        val description: String,
        val genre: String,
        val image: String,
        var rate: Double,
        val ratesNumber: Int,
        val durationTime: Int,
        val releaseDate: String,
        val director: String
) {
    companion object {
        fun fromDomain(movie: Movie) = with(movie) {
            MovieDto(
                    id,
                    title,
                    description,
                    genre,
                    image,
                    rate,
                    ratesNumber,
                    durationTime,
                    releaseDate.format(DateTimeFormatter.ISO_DATE_TIME),
                    director
            )
        }
    }
}