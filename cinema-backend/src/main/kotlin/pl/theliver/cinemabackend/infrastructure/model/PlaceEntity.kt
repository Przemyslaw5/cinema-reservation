package pl.theliver.cinemabackend.infrastructure.model

import pl.theliver.cinemabackend.domain.Place
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.RateCrudRepositoryJpa
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.ReservationCrudRepositoryJpa
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.SeanceCrudRepositoryJpa
import javax.persistence.*

@Entity
data class PlaceEntity(
        @Id
        val id: String,
        val number: Int,
        var isReserved: Boolean,
        @ManyToOne
        @JoinColumn(name = "seance_id")
        val seance: SeanceEntity,
        @ManyToOne
        @JoinColumn(name = "reservation_id")
        val reservation: ReservationEntity
) {
    fun toDomain() = Place(id, number, isReserved, seance.id, reservation.id)

    companion object {
        fun fromDomain(
                place: Place,
                seanceCrudRepositoryJpa: SeanceCrudRepositoryJpa,
                reservationCrudRepositoryJpa: ReservationCrudRepositoryJpa
        ) = with(place) {
            PlaceEntity(
                    id,
                    number,
                    isReserved,
                    seanceCrudRepositoryJpa.findById(seanceId).get(),
                    reservationCrudRepositoryJpa.findById(reservationId).get()
            )
        }
    }
}
