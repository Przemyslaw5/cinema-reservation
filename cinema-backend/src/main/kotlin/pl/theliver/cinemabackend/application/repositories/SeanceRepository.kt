package pl.theliver.cinemabackend.application.repositories

import pl.theliver.cinemabackend.domain.Seance

interface SeanceRepository {
    fun saveSeance(seance: Seance)
    fun getAllSeances(): List<Seance>
    fun getSeanceById(id: String): Seance
    fun getAllSeancesByMovieId(id: String): List<Seance>
}