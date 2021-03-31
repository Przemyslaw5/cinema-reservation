package pl.theliver.cinemabackend.application.services

import org.springframework.stereotype.Service
import pl.theliver.cinemabackend.application.repositories.PlaceRepository
import pl.theliver.cinemabackend.domain.Place
import pl.theliver.cinemabackend.infrastructure.repositoryJpaImpl.PlaceRepositoryJpa
import javax.transaction.Transactional

@Service
@Transactional
class PlaceService(
        private val placeRepository: PlaceRepository
) {

    fun addPlace(place: Place) = placeRepository.savePlace(place)

    fun getAllPlaces() = placeRepository.getAllPlaces()

    fun getPlaceById(id: String) = placeRepository.getPlaceById(id)

    fun getAllPlacesBySeanceId(seanceId: String) = placeRepository.getAllPlacesBySeanceId(seanceId)
}