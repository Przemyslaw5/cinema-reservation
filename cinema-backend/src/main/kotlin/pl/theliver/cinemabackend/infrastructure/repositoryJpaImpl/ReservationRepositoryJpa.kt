package pl.theliver.cinemabackend.infrastructure.repositoryJpaImpl

import org.springframework.stereotype.Component
import pl.theliver.cinemabackend.application.repositories.ReservationRepository
import pl.theliver.cinemabackend.domain.Reservation
import pl.theliver.cinemabackend.domain.User
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.RateCrudRepositoryJpa
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.ReservationCrudRepositoryJpa
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.SeanceCrudRepositoryJpa
import pl.theliver.cinemabackend.infrastructure.model.ReservationEntity
import pl.theliver.cinemabackend.infrastructure.model.UserEntity

@Component
class ReservationRepositoryJpa(
        private val reservationCrudRepositoryJpa: ReservationCrudRepositoryJpa,
        private val rateCrudRepositoryJpa: RateCrudRepositoryJpa,
        private val seanceCrudRepositoryJpa: SeanceCrudRepositoryJpa
) : ReservationRepository {
    override fun saveReservation(reservation: Reservation) {
        reservationCrudRepositoryJpa.save(ReservationEntity.fromDomain(reservation, reservationCrudRepositoryJpa, rateCrudRepositoryJpa, seanceCrudRepositoryJpa))
    }

    override fun getAllReservations() = reservationCrudRepositoryJpa.findAll().map { it.toDomain() }

    override fun getReservationById(id: String) = reservationCrudRepositoryJpa.findById(id).orElse(null).toDomain()

//    override fun getReservationByUser(user: User) =
//            reservationCrudRepositoryJpa.getAllReservationByUserEntity(
//                    UserEntity.fromDomain(user, reservationCrudRepositoryJpa, rateCrudRepositoryJpa))
//                    .map { it.toDomain() }
}