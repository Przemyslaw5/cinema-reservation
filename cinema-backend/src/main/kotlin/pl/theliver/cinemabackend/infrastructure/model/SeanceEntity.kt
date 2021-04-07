package pl.theliver.cinemabackend.infrastructure.model

import pl.theliver.cinemabackend.domain.*
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.*
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity(name = "Seance")
data class SeanceEntity(
        @Id
        val id: String = UUID.randomUUID().toString(),
        val startDate: LocalDateTime,
        @OneToMany(mappedBy = "seance", fetch = FetchType.LAZY)
        val places: List<PlaceEntity> = emptyList(),
        @ManyToOne
        @JoinColumn(name = "movie_id")
        val movie: MovieEntity,
        @ManyToOne
        @JoinColumn(name = "screening_room_id")
        var screeningRoom: ScreeningRoomEntity,
        @OneToMany(mappedBy = "seance")
        var reservations: List<ReservationEntity> = emptyList()

) {
    fun toDomain() = Seance(
            id,
            startDate,
            places.map { it.toDomain() }.toMutableList(),
            movie.id,
            screeningRoom.id,
            reservations.map { it.id }.toMutableList()
    )

    companion object {
        fun fromDomain(
                seance: Seance,
                movieCrudRepositoryJpa: MovieCrudRepositoryJpa,
                screeningRoomCrudRepositoryJpa: ScreeningRoomCrudRepositoryJpa,
                seanceCrudRepositoryJpa: SeanceCrudRepositoryJpa,
                reservationCrudRepositoryJpa: ReservationCrudRepositoryJpa,
        ) = with(seance) {
            SeanceEntity(
                    id,
                    startDate,
                    places.map { PlaceEntity.fromDomain(it, seanceCrudRepositoryJpa, reservationCrudRepositoryJpa) },
                    movieCrudRepositoryJpa.findById(movieId).get(),
                    screeningRoomCrudRepositoryJpa.findById(screeningRoomId).get(),
                    reservationsIds.map { reservationCrudRepositoryJpa.findById(it).get() }

            )
        }
    }
}
