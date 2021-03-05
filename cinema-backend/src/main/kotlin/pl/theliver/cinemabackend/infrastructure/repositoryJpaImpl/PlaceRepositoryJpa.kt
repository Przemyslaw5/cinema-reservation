package pl.theliver.cinemabackend.infrastructure.repositoryJpaImpl

import org.springframework.stereotype.Component
import pl.theliver.cinemabackend.application.repositories.PlaceRepository
import pl.theliver.cinemabackend.domain.Place
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.PlaceCrudRepositoryJpa
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.ReservationCrudRepositoryJpa
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.SeanceCrudRepositoryJpa
import pl.theliver.cinemabackend.infrastructure.model.PlaceEntity

@Component
class PlaceRepositoryJpa(
        private val placeCrudRepositoryJpa: PlaceCrudRepositoryJpa,
        private val seanceCrudRepositoryJpa: SeanceCrudRepositoryJpa,
        private val reservationCrudRepositoryJpa: ReservationCrudRepositoryJpa
) : PlaceRepository {
    override fun savePlace(place: Place) {
        placeCrudRepositoryJpa.save(PlaceEntity.fromDomain(place, seanceCrudRepositoryJpa, reservationCrudRepositoryJpa))
    }

    override fun getAllPlaces() = placeCrudRepositoryJpa.findAll().map { it.toDomain() }

    override fun getPlaceById(id: String) = placeCrudRepositoryJpa.findById(id).orElse(null).toDomain()
}