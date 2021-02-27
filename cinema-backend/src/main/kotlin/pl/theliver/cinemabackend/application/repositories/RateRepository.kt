package pl.theliver.cinemabackend.application.repositories

import pl.theliver.cinemabackend.domain.Rate

interface RateRepository {
    fun saveRate(rate: Rate)
    fun getAllRates(): List<Rate>
    fun getRateById(id: String): Rate
}