package pl.theliver.cinemabackend.presentation.model

import pl.theliver.cinemabackend.application.services.RateService
import pl.theliver.cinemabackend.application.services.SeanceService
import pl.theliver.cinemabackend.domain.Movie
import java.time.LocalDate
import java.util.*

data class MovieDto(
        val id: String = UUID.randomUUID().toString(),
        val title: String,
        val description: String,
        val genre: String,
        val image: String,
        var rate: Double,
        val ratesNumber: Int,
        val durationTime: Int,
        val releaseDate: LocalDate,
        val director: String
) {
    fun toDomain(
            seanceService: SeanceService,
            rateService: RateService
    ) = Movie(
            id,
            title,
            description,
            genre,
            image,
            rate,
            ratesNumber,
            durationTime,
            seanceService.getAllSeances().filter { it.movieId == id }.map { it.id }.toMutableList(),
            releaseDate,
            director,
            rateService.getAllRates().filter { it.movie.id == id }.map { it.id }.toMutableList()
    )

    companion object {
        fun fromDomain(movie: Movie) = with(movie) {
            MovieDto(
                    id,
                    title,
                    description,
                    genre,
                    image,
                    rate,
                    ratesNumber,
                    durationTime,
                    releaseDate,
                    director
            )
        }
    }
}