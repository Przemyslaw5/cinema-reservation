package pl.theliver.cinemabackend.presentation

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import pl.theliver.cinemabackend.application.services.PlaceService
import pl.theliver.cinemabackend.application.services.ScreeningRoomService
import pl.theliver.cinemabackend.presentation.model.ScreeningRoomDto

@RestController
@CrossOrigin
class ScreeningRoomController(
        private val screeningRoomService: ScreeningRoomService,
        private val placeService: PlaceService
) {

    @GetMapping("/screening-rooms/{id}")
    fun getScreeningRoom(@PathVariable("id") id: String) =
            ScreeningRoomDto.fromDomain(screeningRoomService.getScreeningRoomById(id))

    @GetMapping("/screening-rooms/{id}/places")
    fun getPlacesFromSeanceId(@PathVariable("id") id: String) =
            placeService.getAllPlaces().filter { it.seanceId == id }
}