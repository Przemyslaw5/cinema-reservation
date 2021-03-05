package pl.theliver.cinemabackend.infrastructure.model

import pl.theliver.cinemabackend.domain.*
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.MovieCrudRepositoryJpa
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.ReservationCrudRepositoryJpa
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.ScreeningRoomCrudRepositoryJpa
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.SeanceCrudRepositoryJpa
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
data class SeanceEntity(
        @Id
        val id: String = UUID.randomUUID().toString(),
        val startDate: LocalDateTime,
        @OneToMany(mappedBy = "seance", fetch = FetchType.LAZY)
        val places: List<PlaceEntity>,
        @ManyToOne
        @JoinColumn(name = "movie_id")
        val movie: MovieEntity,
        @ManyToOne
        @JoinColumn(name = "screening_room_id")
        var screeningRoom: ScreeningRoomEntity,
) {
    fun toDomain() = Seance(id, startDate, places.map { it.toDomain() }.toMutableList(), movie.id, screeningRoom.id)

    companion object {
        fun fromDomain(
                seance: Seance,
                movieCrudRepositoryJpa: MovieCrudRepositoryJpa,
                screeningRoomCrudRepositoryJpa: ScreeningRoomCrudRepositoryJpa,
                seanceCrudRepositoryJpa: SeanceCrudRepositoryJpa,
                reservationCrudRepositoryJpa: ReservationCrudRepositoryJpa
        ) = with(seance) {
            SeanceEntity(
                    id,
                    startDate,
                    places.map { PlaceEntity.fromDomain(it, seanceCrudRepositoryJpa, reservationCrudRepositoryJpa) },
                    movieCrudRepositoryJpa.findById(movieId).get(),
                    screeningRoomCrudRepositoryJpa.findById(screeningRoomId).get()
            )
        }
    }
}
