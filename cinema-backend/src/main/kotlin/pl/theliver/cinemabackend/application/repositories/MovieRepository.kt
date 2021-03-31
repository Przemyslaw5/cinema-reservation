package pl.theliver.cinemabackend.application.repositories

import pl.theliver.cinemabackend.domain.Movie

interface MovieRepository {
    fun saveMovie(movie: Movie)
    fun getAllMovies(): List<Movie>
    fun getMovieById(id: String): Movie
    fun getMovieByTitle(title: String): List<Movie>
}