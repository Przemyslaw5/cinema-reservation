package pl.theliver.cinemabackend.application.services

import org.springframework.stereotype.Service
import pl.theliver.cinemabackend.application.repositories.MovieRepository
import pl.theliver.cinemabackend.domain.Movie
import pl.theliver.cinemabackend.infrastructure.repositoryJpaImpl.MovieRepositoryJpa
import javax.transaction.Transactional

@Service
@Transactional
class MovieService(
    private val movieRepository: MovieRepository
) {

    fun addMovie(movie: Movie) = movieRepository.saveMovie(movie)

    fun getAllMovies() = movieRepository.getAllMovies()

    fun getMovieById(id: String) = movieRepository.getMovieById(id)

    fun isMovieExists(movie: Movie): Boolean {
        return movieRepository.getMovieByTitle(movie.title).isNotEmpty()
    }

    fun createNewMovieIfTitleNotExists(newMovie: Movie): Boolean {

        if (!isMovieExists(newMovie)) {
            movieRepository.saveMovie(newMovie)
            return true
        }
        return false
    }
}