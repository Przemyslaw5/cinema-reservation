package pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import pl.theliver.cinemabackend.infrastructure.model.PlaceEntity

@Repository
interface PlaceCrudRepositoryJpa : CrudRepository<PlaceEntity, String> {
}