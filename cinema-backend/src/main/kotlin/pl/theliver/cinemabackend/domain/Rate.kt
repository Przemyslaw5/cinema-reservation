package pl.theliver.cinemabackend.domain

data class Rate(
        val id: String,
        val user: User,
        val userRate: Double,
        val movie: Movie
)
