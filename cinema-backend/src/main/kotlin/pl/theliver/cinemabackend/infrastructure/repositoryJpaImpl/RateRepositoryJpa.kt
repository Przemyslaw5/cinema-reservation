package pl.theliver.cinemabackend.infrastructure.repositoryJpaImpl

import org.springframework.stereotype.Component
import pl.theliver.cinemabackend.application.repositories.RateRepository
import pl.theliver.cinemabackend.domain.Rate
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.RateCrudRepositoryJpa
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.ReservationCrudRepositoryJpa
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.SeanceCrudRepositoryJpa
import pl.theliver.cinemabackend.infrastructure.model.RateEntity

@Component
class RateRepositoryJpa(
        private val rateCrudRepositoryJpa: RateCrudRepositoryJpa,
        private val reservationCrudRepositoryJpa: ReservationCrudRepositoryJpa,
        private val seanceCrudRepositoryJpa: SeanceCrudRepositoryJpa
        ) : RateRepository {
    override fun saveRate(rate: Rate) {
        rateCrudRepositoryJpa.save(RateEntity.fromDomain(rate, rateCrudRepositoryJpa, reservationCrudRepositoryJpa, seanceCrudRepositoryJpa))
    }

    override fun getAllRates() = rateCrudRepositoryJpa.findAll().map { it.toDomain() }

    override fun getRateById(id: String) = rateCrudRepositoryJpa.findById(id).orElse(null).toDomain()
}