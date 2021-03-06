package pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import pl.theliver.cinemabackend.infrastructure.model.ReservationEntity

@Repository
interface ReservationCrudRepositoryJpa : CrudRepository<ReservationEntity, String> {
    fun getAllReservationByUserId(id: String): List<ReservationEntity>
    fun getAllReservationsBySeanceId(id: String): List<ReservationEntity>
}