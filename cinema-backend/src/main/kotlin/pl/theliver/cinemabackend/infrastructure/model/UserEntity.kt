package pl.theliver.cinemabackend.infrastructure.model

import pl.theliver.cinemabackend.domain.User
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.RateCrudRepositoryJpa
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.ReservationCrudRepositoryJpa
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity(name = "User")
data class UserEntity(

        @Id
        val id: String = UUID.randomUUID().toString(),
        val username: String,
        val leadingQuestion: String,
        val leadingAnswer: String,
        @OneToMany(mappedBy = "user")
        val reservations: List<ReservationEntity> = emptyList(),
        @OneToMany(mappedBy = "user")
        val rates: List<RateEntity> = emptyList()
) {
    fun toDomain() = User(
            id,
            username,
            leadingQuestion,
            leadingAnswer,
            reservations.map { it.id }.toMutableList(),
            rates.map { it.id }.toMutableList()
    )

    companion object {
        fun fromDomain(
                user: User,
                reservationCrudRepositoryJpa: ReservationCrudRepositoryJpa,
                rateCrudRepositoryJpa: RateCrudRepositoryJpa
        ) = with(user) {
            UserEntity(
                    id,
                    username,
                    leadingQuestion,
                    leadingAnswer,
                    reservationsIds.map { reservationCrudRepositoryJpa.findById(it).get() },
                    ratesIds.map { rateCrudRepositoryJpa.findById(it).get() }
            )
        }
    }
}
