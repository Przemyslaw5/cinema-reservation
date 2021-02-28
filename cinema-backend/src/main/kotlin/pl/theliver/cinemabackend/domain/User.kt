package pl.theliver.cinemabackend.domain

import java.util.*

data class User(
        val id: String = UUID.randomUUID().toString(),
        val username: String,
        val leadingQuestion: String,
        val leadingAnswer: String,
        val reservationsIds: List<String>,
        val ratesIds: List<String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

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
