package pl.theliver.cinemabackend.infrastructure.model

import pl.theliver.cinemabackend.domain.Movie
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.RateCrudRepositoryJpa
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.SeanceCrudRepositoryJpa
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity(name = "Movie")
data class MovieEntity(
        @Id
        val id: String = UUID.randomUUID().toString(),
        val title: String,
        val description: String,
        val genre: String,
        val image: String,
        var rate: Double = 0.0,
        var ratesNumber: Int = 0,
        val durationTime: Int,
        @OneToMany(mappedBy = "movie")
        var seances: List<SeanceEntity> = emptyList(),
        val releaseDate: LocalDate,
        val director: String,
        @OneToMany(mappedBy = "movie")
        var rates: List<RateEntity> = emptyList()
) {

    fun toDomain() = Movie(
            id,
            title,
            description,
            genre,
            image,
            rate,
            ratesNumber,
            durationTime,
            seances.map { it.id }.toMutableList(),
            releaseDate,
            director,
            rates.map { it.id }.toMutableList()
    )

    companion object {
        fun fromDomain(
                movie: Movie,
                seanceCrudRepositoryJpa: SeanceCrudRepositoryJpa,
                rateCrudRepositoryJpa: RateCrudRepositoryJpa
        ) = with(movie) {
            MovieEntity(
                    id,
                    title,
                    description,
                    genre,
                    image,
                    rate,
                    ratesNumber,
                    durationTime,
                    seancesIds.map { seanceCrudRepositoryJpa.findById(it).get() },
                    releaseDate,
                    director,
                    ratesIds.map { rateCrudRepositoryJpa.findById(it).get() }
            )
        }
    }
}