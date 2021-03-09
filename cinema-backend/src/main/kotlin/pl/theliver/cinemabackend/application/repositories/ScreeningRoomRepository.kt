package pl.theliver.cinemabackend.application.repositories

import pl.theliver.cinemabackend.domain.ScreeningRoom

interface ScreeningRoomRepository {
    fun saveScreeningRoom(screeningRoom: ScreeningRoom)
    fun getAllScreeningRooms(): List<ScreeningRoom>
    fun getScreeningRoomById(id: String): ScreeningRoom
    fun getScreeningRoomByName(name: String): ScreeningRoom
}