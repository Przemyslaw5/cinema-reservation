package pl.theliver.cinemabackend.presentation.model

import pl.theliver.cinemabackend.domain.User

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

    fun createNewUser() = User(
            username = username,
            leadingQuestion = leadingQuestion,
            leadingAnswer = leadingAnswer
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserDto

        if (username != other.username) return false
        if (leadingQuestion != other.leadingQuestion) return false
        if (leadingAnswer != other.leadingAnswer) return false

        return true
    }

    override fun hashCode(): Int {
        var result = username.hashCode()
        result = 31 * result + leadingQuestion.hashCode()
        result = 31 * result + leadingAnswer.hashCode()
        return result
    }
}