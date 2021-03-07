//package pl.theliver.cinemabackend.presentation
//
//import org.springframework.http.HttpStatus
//import org.springframework.http.ResponseEntity
//import org.springframework.web.bind.annotation.*
//import pl.theliver.cinemabackend.application.services.ReservationService
//import pl.theliver.cinemabackend.presentation.model.ReservationDto
//import pl.theliver.cinemabackend.presentation.model.UserDto
//
//@RestController
//@CrossOrigin
//class ReservationController(
//        private val reservationService: ReservationService
//) {
//
//    @PostMapping("/reservations")
//    fun getReservationsFromUser(@RequestBody userDto: UserDto) =
//        ResponseEntity(reservationService.getAllReservationFromUser(userDto.createNewUser())
//                .map { ReservationDto.fromDomain(it) }, HttpStatus.OK)
//}