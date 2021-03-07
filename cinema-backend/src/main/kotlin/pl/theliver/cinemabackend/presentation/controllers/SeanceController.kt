package pl.theliver.cinemabackend.presentation.controllers

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import pl.theliver.cinemabackend.application.services.SeanceService
import pl.theliver.cinemabackend.presentation.model.SeanceDto

@RestController
@CrossOrigin
class SeanceController(
        private val seanceService: SeanceService,
) {

    @GetMapping("/movies/{id}/seances")
    fun getSeancesFromMovieId(@PathVariable("id") id: String): List<SeanceDto> {
        val data = seanceService.getSeancesByMovieIdAndDictForRoom(id)
        val seances = data.first
        val namesDict = data.second
        return seances.map { SeanceDto.fromDomain(it, namesDict[it.screeningRoomId]!!) }
    }
}