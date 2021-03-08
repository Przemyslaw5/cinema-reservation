package pl.theliver.cinemabackend.infrastructure.repositoryJpaImpl

import org.springframework.stereotype.Component
import pl.theliver.cinemabackend.application.repositories.ReservationRepository
import pl.theliver.cinemabackend.domain.Reservation
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.*
import pl.theliver.cinemabackend.infrastructure.model.ReservationEntity

@Component
class ReservationRepositoryJpa(
        private val reservationCrudRepositoryJpa: ReservationCrudRepositoryJpa,
        private val rateCrudRepositoryJpa: RateCrudRepositoryJpa,
        private val seanceCrudRepositoryJpa: SeanceCrudRepositoryJpa,
        private val movieCrudRepositoryJpa: MovieCrudRepositoryJpa,
        private val screeningRoomCrudRepositoryJpa: ScreeningRoomCrudRepositoryJpa
) : ReservationRepository {
    override fun saveReservation(reservation: Reservation) {
        reservationCrudRepositoryJpa.save(ReservationEntity.fromDomain(
                reservation,
                reservationCrudRepositoryJpa,
                rateCrudRepositoryJpa,
                seanceCrudRepositoryJpa,
                movieCrudRepositoryJpa,
                screeningRoomCrudRepositoryJpa
        ))
    }

    override fun getAllReservations() = reservationCrudRepositoryJpa.findAll().map { it.toDomain() }

    override fun getReservationById(id: String) =
            reservationCrudRepositoryJpa.findById(id).orElse(null).toDomain()

    override fun getReservationByUserId(id: String) =
            reservationCrudRepositoryJpa.getAllReservationByUserId(id).map { it.toDomain() }
}