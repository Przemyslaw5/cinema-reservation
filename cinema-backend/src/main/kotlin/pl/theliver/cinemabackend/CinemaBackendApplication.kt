package pl.theliver.cinemabackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CinemaBackendApplication

fun main(args: Array<String>) {
    runApplication<CinemaBackendApplication>(*args)
}
