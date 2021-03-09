package pl.theliver.cinemabackend.infrastructure.repositoryJpaImpl

import org.springframework.stereotype.Component
import pl.theliver.cinemabackend.application.repositories.SeanceRepository
import pl.theliver.cinemabackend.domain.Seance
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.MovieCrudRepositoryJpa
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.ReservationCrudRepositoryJpa
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.ScreeningRoomCrudRepositoryJpa
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.SeanceCrudRepositoryJpa
import pl.theliver.cinemabackend.infrastructure.model.SeanceEntity

@Component
class SeanceRepositoryJpa(
        private val seanceCrudRepositoryJpa: SeanceCrudRepositoryJpa,
        private val movieCrudRepositoryJpa: MovieCrudRepositoryJpa,
        private val screeningRoomCrudRepositoryJpa: ScreeningRoomCrudRepositoryJpa,
        private val reservationCrudRepositoryJpa: ReservationCrudRepositoryJpa
) : SeanceRepository {
    override fun saveSeance(seance: Seance) {
        seanceCrudRepositoryJpa.save(SeanceEntity.fromDomain(
                seance,
                movieCrudRepositoryJpa,
                screeningRoomCrudRepositoryJpa,
                seanceCrudRepositoryJpa,
                reservationCrudRepositoryJpa
        ))
    }

    override fun getAllSeances() = seanceCrudRepositoryJpa.findAll().map { it.toDomain() }

    override fun getSeanceById(id: String) = seanceCrudRepositoryJpa.findById(id).orElse(null).toDomain()

    override fun getAllSeancesByMovieId(id: String): List<Seance> =
            seanceCrudRepositoryJpa.getAllSeancesByMovieId(id).map { it.toDomain() }

    override fun getAllSeancesByScreeningRoomId(id: String) =
        seanceCrudRepositoryJpa.getAllSeancesByScreeningRoomId(id).map { it.toDomain() }
}