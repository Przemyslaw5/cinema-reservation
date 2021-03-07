package pl.theliver.cinemabackend.presentation.controllers

import org.springframework.http.HttpStatus

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import org.springframework.web.server.ResponseStatusException

import pl.theliver.cinemabackend.application.services.UserService
import pl.theliver.cinemabackend.presentation.model.UserDto


@RestController
@CrossOrigin(origins = ["http://localhost:4200"])
class UserController(
    private val userService: UserService
) {

    @PostMapping(path = ["/register"])
    fun registerNewUser(@RequestBody userDto: UserDto): ResponseEntity<Boolean?>? {
        val isCrated = userService.createUserIfNotExist(userDto.createNewUser())

        if (isCrated) {
            return ResponseEntity(true, HttpStatus.OK)
        }
        else {
            throw ResponseStatusException(
                    HttpStatus.CONFLICT, "User already exists with this username"
            )
        }
    }

    @PostMapping(path = ["/login"])
    fun login(@RequestBody userDto: UserDto): ResponseEntity<Boolean?>? {
        val user = userService.getUserByUsername(userDto.username)

        if (user != null && UserDto.fromDomain(user) == userDto) {
            return ResponseEntity(true, HttpStatus.OK)
        }
        throw ResponseStatusException(
                HttpStatus.CONFLICT, "Login or answer is incorrect! Maybe you choose other question?"
        )
    }
}