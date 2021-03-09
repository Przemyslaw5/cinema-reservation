package pl.theliver.cinemabackend.presentation.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.theliver.cinemabackend.application.services.MovieService
import pl.theliver.cinemabackend.domain.MovieGenre
import pl.theliver.cinemabackend.presentation.model.NewMovieDto
import pl.theliver.cinemabackend.presentation.model.ReservationDto

@RestController
@CrossOrigin
class MovieController(
        private val movieService: MovieService
) {
    @GetMapping("/movies")
    fun getAllMovies() = movieService.getAllMovies()


    @GetMapping("/movies/{id}")
    fun getMovie(@PathVariable("id") id: String) = movieService.getMovieById(id)

    @GetMapping("/genres")
    fun getAllGenres() = MovieGenre.values()

    @PostMapping("/movie/add")
    fun getReservationsFromUser(@RequestBody newMovieDto: NewMovieDto): ResponseEntity<Boolean> {

        return ResponseEntity(
            movieService.createNewMovieIfTitleNotExists(newMovieDto.toDomain()), HttpStatus.OK
        )
    }

}

