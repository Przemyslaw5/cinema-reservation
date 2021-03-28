package pl.theliver.cinemabackend.application.services

import org.springframework.stereotype.Service
import pl.theliver.cinemabackend.application.repositories.RateRepository
import pl.theliver.cinemabackend.domain.Rate
import pl.theliver.cinemabackend.infrastructure.repositoryJpaImpl.RateRepositoryJpa
import javax.transaction.Transactional

@Service
@Transactional
class RateService(
        private val rateRepository: RateRepository
) {

    fun addRate(rate: Rate) = rateRepository.saveRate(rate)

    fun getAllRates() = rateRepository.getAllRates()

    fun getRateById(id: String) = rateRepository.getRateById(id)
}