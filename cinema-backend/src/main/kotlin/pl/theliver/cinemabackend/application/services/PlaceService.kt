package pl.theliver.cinemabackend.application.services

import org.springframework.stereotype.Service
import pl.theliver.cinemabackend.domain.Place
import pl.theliver.cinemabackend.infrastructure.repositoryJpaImpl.PlaceRepositoryJpa
import javax.transaction.Transactional

@Service
@Transactional
class PlaceService(
        private val placeRepositoryJpa: PlaceRepositoryJpa
) {

    fun addPlace(place: Place) = placeRepositoryJpa.savePlace(place)

    fun getAllPlaces() = placeRepositoryJpa.getAllPlaces()

    fun getPlaceById(id: String) = placeRepositoryJpa.getPlaceById(id)

    fun getAllPlacesBySeanceId(seanceId: String) = placeRepositoryJpa.getAllPlacesBySeanceId(seanceId)
}