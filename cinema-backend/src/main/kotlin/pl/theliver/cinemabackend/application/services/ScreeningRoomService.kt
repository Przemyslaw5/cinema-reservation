package pl.theliver.cinemabackend.application.services

import org.springframework.stereotype.Service
import pl.theliver.cinemabackend.application.repositories.ScreeningRoomRepository
import pl.theliver.cinemabackend.domain.ScreeningRoom
import javax.transaction.Transactional

@Service
@Transactional
class ScreeningRoomService(
        private val screeningRoomRepository: ScreeningRoomRepository
) {

    fun addScreeningRoom(screeningRoom: ScreeningRoom) = screeningRoomRepository.saveScreeningRoom(screeningRoom)

    fun getAllScreeningRooms() = screeningRoomRepository.getAllScreeningRooms()

    fun getScreeningRoomById(id: String) = screeningRoomRepository.getScreeningRoomById(id)
}