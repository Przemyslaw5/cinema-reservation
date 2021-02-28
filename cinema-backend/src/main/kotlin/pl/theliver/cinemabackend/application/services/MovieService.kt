package pl.theliver.cinemabackend.application.services

import org.springframework.stereotype.Service
import pl.theliver.cinemabackend.domain.Movie
import pl.theliver.cinemabackend.infrastructure.repositoryJpaImpl.MovieRepositoryJpa
import javax.transaction.Transactional

@Service
@Transactional
class MovieService(
    private val movieRepositoryJpa: MovieRepositoryJpa
) {

    fun addMovie(movie: Movie) = movieRepositoryJpa.saveMovie(movie)

    fun getAllMovies() = movieRepositoryJpa.getAllMovies()

    fun getMovieById(id: String) = movieRepositoryJpa.getMovieById(id)
}