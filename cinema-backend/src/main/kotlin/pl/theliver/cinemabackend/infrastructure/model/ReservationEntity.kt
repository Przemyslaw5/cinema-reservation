package pl.theliver.cinemabackend.infrastructure.model

import pl.theliver.cinemabackend.domain.Reservation
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.RateCrudRepositoryJpa
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.ReservationCrudRepositoryJpa
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.SeanceCrudRepositoryJpa
import javax.persistence.*

@Entity
data class ReservationEntity(
        @Id
        val id: String,
        @ManyToOne
        @JoinColumn(name = "user_id")
        var user: UserEntity,
        @OneToMany(mappedBy = "reservation")
        var places: Collection<PlaceEntity>,
        var secretWord: String
) {
    fun toDomain() = Reservation(
            id = id,
            user = user.toDomain(),
            places = places.map { it.toDomain() },
            secretWord = secretWord
    )

    companion object {
        fun fromDomain(
                reservation: Reservation,
                reservationCrudRepositoryJpa: ReservationCrudRepositoryJpa,
                rateCrudRepositoryJpa: RateCrudRepositoryJpa,
                seanceCrudRepositoryJpa: SeanceCrudRepositoryJpa
        ) = with(reservation) {
            ReservationEntity(
                    id,
                    UserEntity.fromDomain(user, reservationCrudRepositoryJpa, rateCrudRepositoryJpa),
                    places.map {
                        PlaceEntity.fromDomain(it, seanceCrudRepositoryJpa,
                                reservationCrudRepositoryJpa)
                    },
                    secretWord
            )
        }
    }
}
