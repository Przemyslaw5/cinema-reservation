package pl.theliver.cinemabackend.presentation.model

import pl.theliver.cinemabackend.domain.User
import java.util.*

data class UserDto(
        val id: String,
        val username: String,
        val leadingQuestion: String,
        val leadingAnswer: String
) {
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