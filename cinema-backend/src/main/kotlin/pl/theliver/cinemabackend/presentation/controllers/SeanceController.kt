package pl.theliver.cinemabackend.presentation.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.theliver.cinemabackend.application.services.ScreeningRoomService
import pl.theliver.cinemabackend.application.services.SeanceService
import pl.theliver.cinemabackend.presentation.model.NewSeanceDto
import pl.theliver.cinemabackend.presentation.model.SeanceDto

@RestController
@CrossOrigin
class SeanceController(
    private val seanceService: SeanceService,
    private val screeningRoomService: ScreeningRoomService
) {

    @GetMapping("/movies/{id}/seances")
    fun getSeancesFromMovieId(@PathVariable("id") id: String): List<SeanceDto> {
        val (seances, namesDict) = seanceService.getSeancesByMovieIdAndDictForRoom(id)
        return seances.map { SeanceDto.fromDomain(it, namesDict[it.screeningRoomId]!!) }
    }

    @PostMapping("/seance/add")
    fun addNewSeance(@RequestBody newSeanceDto: NewSeanceDto): ResponseEntity<SeanceDto> {

        val screeningRoomId = screeningRoomService.getScreeningRoomByName(newSeanceDto.screeningRoomName).id

        return when (val seance =
            seanceService.createNewSeanceIfNotHoursConflict(newSeanceDto.toDomain(screeningRoomId))) {
            null -> {
                ResponseEntity(HttpStatus.NO_CONTENT)
            }
            else -> ResponseEntity(
                SeanceDto.fromDomain(seance, newSeanceDto.screeningRoomName), HttpStatus.OK
            )
        }
    }

    @DeleteMapping("remove/seance/{id}")
    fun deleteSeance(@PathVariable("id") id: String) = seanceService.deleteSeanceById(id)
}