package pl.theliver.cinemabackend.application.services

import org.springframework.stereotype.Service
import pl.theliver.cinemabackend.application.repositories.SeanceRepository
import pl.theliver.cinemabackend.domain.Seance
import javax.transaction.Transactional

@Service
@Transactional
class SeanceService(
        private val seanceRepository: SeanceRepository
) {

    fun addSeance(seance: Seance) = seanceRepository.saveSeance(seance)

    fun getAllSeances() = seanceRepository.getAllSeances()

    fun getSeanceById(id: String) = seanceRepository.getSeanceById(id)
}