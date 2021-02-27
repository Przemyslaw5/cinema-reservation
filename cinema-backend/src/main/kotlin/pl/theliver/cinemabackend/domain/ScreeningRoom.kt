package pl.theliver.cinemabackend.domain

data class ScreeningRoom(
        val id: String,
        val name: String,
        val placesPlan: Collection<String>,
        val seancesIds: Collection<String>
)
