package pl.theliver.cinemabackend.application.repositories

import pl.theliver.cinemabackend.domain.Place

interface PlaceRepository {
    fun savePlace(place: Place)
    fun getAllPlaces(): List<Place>
    fun getPlaceById(id: String): Place
    fun getAllPlacesBySeanceId(id: String): List<Place>
    fun deleteById(id: String)
}