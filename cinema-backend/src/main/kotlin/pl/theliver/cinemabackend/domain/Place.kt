package pl.theliver.cinemabackend.domain

import java.util.*

data class Place(
        val id: String = UUID.randomUUID().toString(),
        val number: Int,
        var isReserved: Boolean = false,
        val seanceId: String,
        var reservationId: String? = null
)
