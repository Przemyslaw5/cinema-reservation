package pl.theliver.cinemabackend.presentation.model

import pl.theliver.cinemabackend.domain.Movie
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class NewMovieDto(
    val title: String,
    var description: String,
    val genre: String,
    var image: String,
    val durationTime: Int,
    val releaseDate: String,
    val director: String,
) {
    fun toDomain() = Movie(
        title = title,
        description = description,
        genre = genre,
        image = image,
        durationTime = durationTime,
        releaseDate = LocalDate.parse(releaseDate, DateTimeFormatter.ISO_DATE_TIME),
        director = director
    )
}