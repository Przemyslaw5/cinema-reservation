package pl.theliver.cinemabackend.presentation.model

import pl.theliver.cinemabackend.domain.Rate
import pl.theliver.cinemabackend.domain.Reservation
import pl.theliver.cinemabackend.domain.User
import java.util.*

data class UserDto(
        val id: String = UUID.randomUUID().toString(),
        val username: String,
        val leadingQuestion: String,
        val leadingAnswer: String,
        val reservations: Collection<Reservation>,
        val rates: Collection<Rate>
) {
//    fun toDomain() = User(id, username, leadingQuestion, leadingAnswer, reservations, rates)
//
//    companion object {
//        fun fromDomain(user: User) = with(user) { UserDto(id, username, leadingQuestion, leadingAnswer, reservations, rates) }
//    }
}