package pl.theliver.cinemabackend.domain

data class User(
    val id: String,
    val username: String,
    val leadingQuestion: String,
    val leadingAnswer: String,
    val reservationsIds: Collection<String>,
    val ratesIds: Collection<String>
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
