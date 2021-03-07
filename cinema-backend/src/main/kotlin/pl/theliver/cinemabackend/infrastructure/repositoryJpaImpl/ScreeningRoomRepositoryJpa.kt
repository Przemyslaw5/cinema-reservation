package pl.theliver.cinemabackend.infrastructure.repositoryJpaImpl

import org.springframework.stereotype.Component
import pl.theliver.cinemabackend.application.repositories.ScreeningRoomRepository
import pl.theliver.cinemabackend.domain.ScreeningRoom
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.ScreeningRoomCrudRepositoryJpa
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.SeanceCrudRepositoryJpa
import pl.theliver.cinemabackend.infrastructure.model.ScreeningRoomEntity

@Component
class ScreeningRoomRepositoryJpa(
        private val screeningRoomCrudRepositoryJpa: ScreeningRoomCrudRepositoryJpa,
        private val seanceCrudRepositoryJpa: SeanceCrudRepositoryJpa
) : ScreeningRoomRepository {
    override fun saveScreeningRoom(screeningRoom: ScreeningRoom) {
        screeningRoomCrudRepositoryJpa.save(ScreeningRoomEntity.fromDomain(screeningRoom, seanceCrudRepositoryJpa))
    }

    override fun getAllScreeningRooms() = screeningRoomCrudRepositoryJpa.findAll().map { it.toDomain() }

    override fun getScreeningRoomById(id: String) =
            screeningRoomCrudRepositoryJpa.findById(id).orElse(null).toDomain()
}