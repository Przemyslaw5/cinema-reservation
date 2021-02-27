package pl.theliver.cinemabackend.infrastructure.repositoryJpaImpl

import org.springframework.stereotype.Component
import pl.theliver.cinemabackend.application.repositories.MovieRepository
import pl.theliver.cinemabackend.domain.Movie
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.MovieCrudRepositoryJpa
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.RateCrudRepositoryJpa
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.SeanceCrudRepositoryJpa
import pl.theliver.cinemabackend.infrastructure.model.MovieEntity

@Component
class MovieRepositoryJpa(
        private val movieCrudRepositoryJpa: MovieCrudRepositoryJpa,
        private val seanceCrudRepositoryJpa: SeanceCrudRepositoryJpa,
        private val rateCrudRepositoryJpa: RateCrudRepositoryJpa
) : MovieRepository {
    override fun saveMovie(movie: Movie) {
        movieCrudRepositoryJpa.save(MovieEntity.fromDomain(movie, seanceCrudRepositoryJpa, rateCrudRepositoryJpa))
    }

    override fun getAllMovies() = movieCrudRepositoryJpa.findAll().map { it.toDomain() }

    override fun getMovieById(id: String) = movieCrudRepositoryJpa.findById(id).orElse(null).toDomain()
}