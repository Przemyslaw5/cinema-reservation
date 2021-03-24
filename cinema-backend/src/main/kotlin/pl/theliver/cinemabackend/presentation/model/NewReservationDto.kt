package pl.theliver.cinemabackend.presentation.model

import pl.theliver.cinemabackend.domain.Place
import pl.theliver.cinemabackend.domain.Reservation
import pl.theliver.cinemabackend.domain.Seance
import pl.theliver.cinemabackend.domain.User

data class NewReservationDto(
    val placeNumbers: List<Int>,
    var secretWord: String,
    val seanceId: String,
    val username: String,
) {
    fun toDomain(seance: Seance, user: User, places: List<Place>): Reservation {

        placeNumbers.map { places[it - 1].isReserved = true }

        return Reservation(
            user = user,
            places = places.filter { placeNumbers.contains(it.number) },
            secretWord = secretWord,
            seance = seance
        )

    }
}