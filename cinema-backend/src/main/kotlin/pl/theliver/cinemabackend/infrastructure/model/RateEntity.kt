package pl.theliver.cinemabackend.infrastructure.model

import pl.theliver.cinemabackend.domain.Rate
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.RateCrudRepositoryJpa
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.ReservationCrudRepositoryJpa
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.SeanceCrudRepositoryJpa
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class RateEntity(
        @Id
        val id: String,
        @ManyToOne
        @JoinColumn(name = "user_id")
        val user: UserEntity,
        val userRate: Double,
        @ManyToOne
        @JoinColumn(name = "movie_id")
        val movie: MovieEntity
) {
    fun toDomain() = Rate(id, user.toDomain(), userRate, movie.toDomain())

    companion object {
        fun fromDomain(
                rate: Rate,
                rateCrudRepositoryJpa: RateCrudRepositoryJpa,
                reservationCrudRepositoryJpa: ReservationCrudRepositoryJpa,
                seanceCrudRepositoryJpa: SeanceCrudRepositoryJpa
        ) = with(rate) {
            RateEntity(
                    id,
                    UserEntity.fromDomain(user, reservationCrudRepositoryJpa, rateCrudRepositoryJpa),
                    userRate,
                    MovieEntity.fromDomain(movie, seanceCrudRepositoryJpa, rateCrudRepositoryJpa)
            )
        }
    }
}
