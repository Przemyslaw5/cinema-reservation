package pl.theliver.cinemabackend.application.services

import org.springframework.stereotype.Service
import pl.theliver.cinemabackend.application.repositories.ScreeningRoomRepository
import pl.theliver.cinemabackend.application.repositories.SeanceRepository
import pl.theliver.cinemabackend.domain.Seance
import pl.theliver.cinemabackend.infrastructure.repositoryJpaImpl.ScreeningRoomRepositoryJpa
import javax.transaction.Transactional

@Service
@Transactional
class SeanceService(
        private val seanceRepository: SeanceRepository,
        private val screeningRoomRepository: ScreeningRoomRepository
) {

    fun addSeance(seance: Seance) = seanceRepository.saveSeance(seance)

    fun getAllSeances() = seanceRepository.getAllSeances()

    fun getSeanceById(id: String) = seanceRepository.getSeanceById(id)

    fun getSeancesFromMovie(id: String) = seanceRepository.getAllSeancesByMovieId(id)

    fun getSeancesByMovieIdAndDictForRoom(id: String): Pair<List<Seance>, Map<String, String>> {
        return Pair(
                seanceRepository.getAllSeancesByMovieId(id),
                screeningRoomRepository.getAllScreeningRooms().map { it.id to it.name }.toMap()
        )
    }
}