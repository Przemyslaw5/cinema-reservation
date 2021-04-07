package pl.theliver.cinemabackend.infrastructure.model

import pl.theliver.cinemabackend.domain.Reservation
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.*
import java.util.*
import javax.persistence.*

@Entity(name = "Reservation")
data class ReservationEntity(
        @Id
        val id: String = UUID.randomUUID().toString(),
        @ManyToOne
        @JoinColumn(name = "user_id")
        var user: UserEntity,
        @OneToMany(mappedBy = "reservation")
        var places: List<PlaceEntity>,
        var secretWord: String,
        @ManyToOne
        @JoinColumn(name = "seance_id")
        val seance: SeanceEntity
) {
    fun toDomain() = Reservation(
            id = id,
            user = user.toDomain(),
            places = places.map { it.toDomain() },
            secretWord = secretWord,
            seance = seance.toDomain()
    )

    companion object {
        fun fromDomain(
                reservation: Reservation,
                reservationCrudRepositoryJpa: ReservationCrudRepositoryJpa,
                rateCrudRepositoryJpa: RateCrudRepositoryJpa,
                seanceCrudRepositoryJpa: SeanceCrudRepositoryJpa,
                movieCrudRepositoryJpa: MovieCrudRepositoryJpa,
                screeningRoomCrudRepositoryJpa: ScreeningRoomCrudRepositoryJpa
        ) = with(reservation) {
            ReservationEntity(
                    id,
                    UserEntity.fromDomain(user, reservationCrudRepositoryJpa, rateCrudRepositoryJpa),
                    places.map {
                        PlaceEntity.fromDomain(it, seanceCrudRepositoryJpa,
                                reservationCrudRepositoryJpa)
                    },
                    secretWord,
                    SeanceEntity.fromDomain(
                            seance,
                            movieCrudRepositoryJpa,
                            screeningRoomCrudRepositoryJpa,
                            seanceCrudRepositoryJpa,
                            reservationCrudRepositoryJpa
                    )
            )
        }
    }
}
