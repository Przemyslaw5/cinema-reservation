package pl.theliver.cinemabackend.presentation

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import pl.theliver.cinemabackend.application.services.ScreeningRoomService
import pl.theliver.cinemabackend.application.services.SeanceService
import pl.theliver.cinemabackend.presentation.model.SeanceDto

@RestController
@CrossOrigin
class SeanceController(
        private val seanceService: SeanceService,
        private val screeningRoomService: ScreeningRoomService
) {

    @GetMapping("/movies/{id}/seances")
    fun getSeancesFromMovieId(@PathVariable("id") id: String): List<SeanceDto> {
        val data = seanceService.getSeancesByMovieIdAndDictForRoom(id)
        return data.first.map { SeanceDto.fromDomain(it, data.second[it.screeningRoomId]!!) }
    }
}