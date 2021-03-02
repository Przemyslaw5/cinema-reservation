package pl.theliver.cinemabackend.presentation.model

import pl.theliver.cinemabackend.application.services.PlaceService
import pl.theliver.cinemabackend.domain.Seance
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

data class SeanceDto(
        val id: String = UUID.randomUUID().toString(),
        val startDate: DateDto,
        val movieId: String,
        val screeningRoomId: String
) {
    fun toDomain(
            placeService: PlaceService,
    ) = Seance(
            id,
            LocalDate.of(startDate.year, startDate.month, startDate.day).atTime(LocalTime.of(startDate.hour, startDate.min)),
            placeService.getAllPlaces().filter { it.seanceId == id }.toMutableList(),
            movieId,
            screeningRoomId
    )

    companion object {
        fun fromDomain(seance: Seance) = with(seance) {
            SeanceDto(
                    id,
                    DateDto(
                            startDate.year,
                            startDate.monthValue,
                            startDate.dayOfYear,
                            startDate.hour,
                            startDate.minute
                    ),
                    movieId,
                    screeningRoomId
            )
        }
    }
}