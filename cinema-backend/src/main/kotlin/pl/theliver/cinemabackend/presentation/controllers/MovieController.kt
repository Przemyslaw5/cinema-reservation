package pl.theliver.cinemabackend.presentation.controllers

import org.springframework.web.bind.annotation.*
import pl.theliver.cinemabackend.application.services.MovieService
import pl.theliver.cinemabackend.domain.MovieGenre

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
}