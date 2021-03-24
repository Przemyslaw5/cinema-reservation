package pl.theliver.cinemabackend.presentation.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.theliver.cinemabackend.application.services.PlaceService
import pl.theliver.cinemabackend.application.services.ReservationService
import pl.theliver.cinemabackend.application.services.SeanceService
import pl.theliver.cinemabackend.application.services.UserService
import pl.theliver.cinemabackend.presentation.model.NewReservationDto
import pl.theliver.cinemabackend.presentation.model.ReservationDto

@RestController
@CrossOrigin
class ReservationController(
        private val reservationService: ReservationService,
        private val userService: UserService,
        private val seanceService: SeanceService,
        private val placeService: PlaceService
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

    @PostMapping("/addReservation")
    fun addNewReservation(@RequestBody newReservationDto: NewReservationDto): ResponseEntity<Boolean> {
        val seance = seanceService.getSeanceById(newReservationDto.seanceId)
        val user = userService.getUserByUsername(newReservationDto.username)!!

        return ResponseEntity(
            reservationService.addNewReservation(newReservationDto.toDomain(seance, user, placeService.getAllPlacesBySeanceId(newReservationDto.seanceId))),
            HttpStatus.OK
        )
    }
}