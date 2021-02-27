package pl.theliver.cinemabackend.infrastructure.model

import pl.theliver.cinemabackend.domain.ScreeningRoom
import pl.theliver.cinemabackend.domain.Seance
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.SeanceCrudRepositoryJpa
import javax.persistence.*

@Entity
data class ScreeningRoomEntity(
        @Id
        val id: String,
        val name: String,
        @ElementCollection
        var placesPlan: Collection<String>,
        @OneToMany(fetch = FetchType.LAZY, mappedBy="screeningRoom")
        var seances: Collection<SeanceEntity>
) {
    fun toDomain() = ScreeningRoom(
            id,
            name,
            placesPlan,
            seances.map { it.id }
    )

    companion object {
        fun fromDomain(screeningRoom: ScreeningRoom, seanceCrudRepositoryJpa: SeanceCrudRepositoryJpa) = with(screeningRoom) {
            ScreeningRoomEntity(
                    id,
                    name,
                    placesPlan,
                    seancesIds.map { seanceCrudRepositoryJpa.findById(it).get() }
            )
        }
    }
}
