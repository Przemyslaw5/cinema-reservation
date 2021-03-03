package pl.theliver.cinemabackend.presentation

import org.springframework.web.bind.annotation.*
import pl.theliver.cinemabackend.application.services.MovieService
import pl.theliver.cinemabackend.application.services.PlaceService
import pl.theliver.cinemabackend.application.services.ScreeningRoomService
import pl.theliver.cinemabackend.application.services.SeanceService
import pl.theliver.cinemabackend.presentation.model.ScreeningRoomDto
import pl.theliver.cinemabackend.presentation.model.SeanceDto

@RestController
@CrossOrigin
class MovieController(
        private val movieService: MovieService,
        private val seanceService: SeanceService,
        private val screeningRoomService: ScreeningRoomService,
        private val placeService: PlaceService
) {
    @GetMapping("/movies")
    fun getAllMovies() = movieService.getAllMovies()


    @GetMapping("/movies/{id}")
    fun getMovie(@PathVariable("id") id: String) = movieService.getMovieById(id)

    @GetMapping("/movies/{id}/seances")
    fun getSeancesFromMovieId(@PathVariable("id") id: String) = seanceService.getSeancesFromMovie(id).map { SeanceDto.fromDomain(it, screeningRoomService) }

    @GetMapping("/screening-rooms/{id}")
    fun getScreeningRoom(@PathVariable("id") id: String) =
            ScreeningRoomDto.fromDomain(screeningRoomService.getScreeningRoomById(id))

    @GetMapping("/screening-rooms/{id}/places")
    fun getPlacesFromSeanceId(@PathVariable("id") id: String) =
            placeService.getAllPlaces().filter { it.seanceId == id }
}