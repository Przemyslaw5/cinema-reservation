package pl.theliver.cinemabackend.application.services

import org.springframework.stereotype.Service
import pl.theliver.cinemabackend.domain.Rate
import pl.theliver.cinemabackend.infrastructure.repositoryJpaImpl.RateRepositoryJpa
import javax.transaction.Transactional

@Service
@Transactional
class RateService(
        private val rateRepositoryJpa: RateRepositoryJpa
) {

    fun addRate(rate: Rate) = rateRepositoryJpa.saveRate(rate)

    fun getAllRates() = rateRepositoryJpa.getAllRates()

    fun getRateById(id: String) = rateRepositoryJpa.getRateById(id)
}