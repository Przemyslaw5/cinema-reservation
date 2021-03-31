package pl.theliver.cinemabackend.presentation.model

import pl.theliver.cinemabackend.domain.Seance
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class NewSeanceDto(
    val movieId: String,
    val startDate: String,
    val screeningRoomName: String
) {
    fun toDomain(screeningRoomId: String) = Seance(
        startDate = LocalDateTime.parse(startDate, DateTimeFormatter.ISO_DATE_TIME),
        movieId = movieId,
        screeningRoomId = screeningRoomId,
    )
}