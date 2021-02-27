package pl.theliver.cinemabackend.domain

data class Place(
        val id: String,
        val number: Int,
        var isReserved: Boolean,
        val seanceId: String,
        val reservationId: String
)
