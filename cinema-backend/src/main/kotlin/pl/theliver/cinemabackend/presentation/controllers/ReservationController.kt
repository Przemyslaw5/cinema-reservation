package pl.theliver.cinemabackend.presentation.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.theliver.cinemabackend.application.services.ReservationService
import pl.theliver.cinemabackend.application.services.UserService
import pl.theliver.cinemabackend.presentation.model.ReservationDto

@RestController
@CrossOrigin
class ReservationController(
        private val reservationService: ReservationService,
        private val userService: UserService
) {

    @PostMapping("/reservations")
    fun getReservationsFromUser(@RequestBody username: String): ResponseEntity<List<ReservationDto>> {

        val data = reservationService.getAllReservationFromUser(userService.getUserByUsername(username)!!)
        val reservations = data.first
        val namesDict = data.second

        return ResponseEntity(
                reservations.map { ReservationDto.fromDomain(
                        it,
                        namesDict[it.seance.movieId]!!,
                        namesDict[it.seance.screeningRoomId]!!) },
                HttpStatus.OK
        )
    }
}