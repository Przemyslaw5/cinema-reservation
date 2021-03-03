package pl.theliver.cinemabackend.presentation.model

import pl.theliver.cinemabackend.application.services.PlaceService
import pl.theliver.cinemabackend.application.services.ScreeningRoomService
import pl.theliver.cinemabackend.domain.Seance
import java.time.LocalDate
import java.time.LocalTime
import java.util.*
import java.text.DecimalFormat

data class SeanceDto(
        val id: String = UUID.randomUUID().toString(),
        val startDate: DateDto,
        val movieId: String,
        val screeningRoomId: String,
        val screeningRoomName: String
) {
    fun toDomain(
            placeService: PlaceService,
    ) = Seance(
            id,
            LocalDate.of(
                    startDate.year.toInt(),
                    startDate.month.toInt(),
                    startDate.day.toInt()).atTime(LocalTime.of(startDate.hour.toInt(), startDate.min.toInt())),
            placeService.getAllPlaces().filter { it.seanceId == id }.toMutableList(),
            movieId,
            screeningRoomId
    )

    companion object {
        fun fromDomain(seance: Seance,
                       screeningRoomService: ScreeningRoomService
        ) = with(seance) {
            val dmFormat = DecimalFormat("00")

            return@with SeanceDto(
                    id,
                    DateDto(
                            dmFormat.format(startDate.year),
                            dmFormat.format(startDate.monthValue),
                            dmFormat.format(startDate.dayOfMonth),
                            dmFormat.format(startDate.hour),
                            dmFormat.format(startDate.minute)
                    ),
                    movieId,
                    screeningRoomId,
                    screeningRoomService.getScreeningRoomById(screeningRoomId).name
            )
        }
    }
}