package pl.theliver.cinemabackend.domain

data class Reservation(
        val id: String,
        var user: User,
        var places: List<Place>,
        var secretWord: String
)
