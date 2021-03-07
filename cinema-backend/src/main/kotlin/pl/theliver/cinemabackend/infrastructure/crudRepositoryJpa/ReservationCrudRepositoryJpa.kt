package pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import pl.theliver.cinemabackend.domain.User
import pl.theliver.cinemabackend.infrastructure.model.ReservationEntity
import pl.theliver.cinemabackend.infrastructure.model.UserEntity

@Repository
interface ReservationCrudRepositoryJpa : CrudRepository<ReservationEntity, String> {
    fun getAllReservationByUser(user: UserEntity): List<ReservationEntity>
}