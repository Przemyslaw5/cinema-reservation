package pl.theliver.cinemabackend.presentation.model

import pl.theliver.cinemabackend.application.services.RateService
import pl.theliver.cinemabackend.application.services.ReservationService
import pl.theliver.cinemabackend.domain.User
import java.util.*

data class UserDto(
        val id: String = UUID.randomUUID().toString(),
        val username: String,
        val leadingQuestion: String,
        val leadingAnswer: String
) {
    fun toDomain(
            reservationService: ReservationService,
            rateService: RateService
    ) = User(
            id,
            username,
            leadingQuestion,
            leadingAnswer,
            reservationService.getAllReservations().filter { it.user.id == id }.map { it.id }.toMutableList(),
            rateService.getAllRates().filter { it.user.id == id }.map { it.id }.toMutableList()
    )

    companion object {
        fun fromDomain(user: User) = with(user) {
            UserDto(
                    id,
                    username,
                    leadingQuestion,
                    leadingAnswer
            )
        }
    }
}