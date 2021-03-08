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

        val (reservations, movieTitleDict, screeningRoomNamesDict) =
                reservationService.getAllReservationsByUserId(userService.getUserByUsername(username)!!.id)

        return ResponseEntity(
                reservations.map { ReservationDto.fromDomain(
                        it,
                        movieTitleDict[it.seance.movieId]!!,
                        screeningRoomNamesDict[it.seance.screeningRoomId]!!) },
                HttpStatus.OK
        )
    }
}