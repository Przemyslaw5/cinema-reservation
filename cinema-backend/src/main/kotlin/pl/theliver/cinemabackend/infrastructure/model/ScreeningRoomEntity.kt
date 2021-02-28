package pl.theliver.cinemabackend.infrastructure.model

import pl.theliver.cinemabackend.domain.ScreeningRoom
import pl.theliver.cinemabackend.domain.Seance
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.SeanceCrudRepositoryJpa
import java.util.*
import javax.persistence.*

@Entity
data class ScreeningRoomEntity(
        @Id
        val id: String = UUID.randomUUID().toString(),
        val name: String,
        val placeNumber: Int,
        @ElementCollection
        var placesPlan: List<String>,
        @OneToMany(mappedBy="screeningRoom")
        var seances: List<SeanceEntity> = emptyList()
) {
    fun toDomain() = ScreeningRoom(
            id,
            name,
            placeNumber,
            placesPlan.toMutableList(),
            seances.map { it.id }.toMutableList()
    )

    companion object {
        fun fromDomain(screeningRoom: ScreeningRoom, seanceCrudRepositoryJpa: SeanceCrudRepositoryJpa) = with(screeningRoom) {
            ScreeningRoomEntity(
                    id,
                    name,
                    placeNumber,
                    placesPlan,
                    seancesIds.map { seanceCrudRepositoryJpa.findById(it).get() }
            )
        }
    }
}
