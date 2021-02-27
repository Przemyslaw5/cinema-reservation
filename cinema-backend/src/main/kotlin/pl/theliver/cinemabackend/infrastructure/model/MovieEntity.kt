package pl.theliver.cinemabackend.infrastructure.model

import pl.theliver.cinemabackend.domain.Movie
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.RateCrudRepositoryJpa
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.SeanceCrudRepositoryJpa
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity(name = "Movie")
data class MovieEntity(
        @Id
        val id: String,
        val title: String,
        val description: String,
        val genre: String,
        val image: String,
        var rate: Double,
        val ratesNumber: Int,
        val durationTime: Int,
        @OneToMany(mappedBy = "movie")
        val seances: Collection<SeanceEntity>,
        val releaseDate: Date,
        val director: String,
        @OneToMany(mappedBy = "movie")
        val rates: Collection<RateEntity>
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
            seances.map { it.id },
            releaseDate,
            director,
            rates.map { it.id }
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