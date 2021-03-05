package pl.theliver.cinemabackend.presentation.model

import pl.theliver.cinemabackend.application.services.ScreeningRoomService
import pl.theliver.cinemabackend.domain.Seance
import java.time.format.DateTimeFormatter

data class SeanceDto(
        val id: String,
        val startDate: String,
        val movieId: String,
        val screeningRoomId: String,
        val screeningRoomName: String
) {
    companion object {
        fun fromDomain(
                seance: Seance,
                screeningRoomName: String
        ) = with(seance) {
            SeanceDto(
                    id,
                    startDate.format(DateTimeFormatter.ISO_DATE_TIME),
                    movieId,
                    screeningRoomId,
                    screeningRoomName
            )
        }
    }
}