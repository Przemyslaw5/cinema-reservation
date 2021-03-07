package pl.theliver.cinemabackend.presentation.model

import pl.theliver.cinemabackend.domain.Reservation
import java.time.format.DateTimeFormatter

class ReservationDto(
        val id: String,
        val title: String,
        val screeningRoomName: String,
        val date: String,
        val places: List<Int>,
        var secretWord: String,
) {
    companion object {
        fun fromDomain(
                reservation: Reservation,
                title: String,
                screeningRoomName: String
        ) = with(reservation) {
            ReservationDto(
                    id,
                    title,
                    screeningRoomName,
                    seance.startDate.format(DateTimeFormatter.ISO_DATE_TIME),
                    places.map { it.number },
                    secretWord
            )
        }
    }
}

/*
data z godzina
git miejsca
git sekretne słowo
nazwa filmu
nazwa sali
 */