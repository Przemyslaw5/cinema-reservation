package pl.theliver.cinemabackend.bootstrap

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import pl.theliver.cinemabackend.application.services.*
import pl.theliver.cinemabackend.domain.*
import java.time.LocalDate
import java.time.LocalTime
import java.util.logging.Logger

@Component
class DataLoader(
        private val movieService: MovieService,
        private val placeService: PlaceService,
        private val rateService: RateService,
        private val reservationService: ReservationService,
        private val screeningRoomService: ScreeningRoomService,
        private val seanceService: SeanceService,
        private val userService: UserService
) : CommandLineRunner {

    val logger: Logger = Logger.getLogger(DataLoader::class.java.name)

    fun initMovies() {
        val titles = listOf("Interstellar", "Pulp Fiction", "Kalifornia", "Kramer Vs. Kramer", "Inception",
                "Friends With Benefits")
        val descriptions = listOf(
                "Set in a dystopian future where humanity is struggling to survive, the film follows a group of astronauts who travel through a wormhole near Saturn in search of a new home for mankind.",
                "Vincent Vega (John Travolta) and Jules Winnfield (Samuel L. Jackson) are two hit men on the hunt for a briefcase whose contents were stolen from their boss, Marsellus Wallace (Ving Rhames). They run into a few unexpected detours along the road.",
                "The film tells the story of a journalist (Duchovny) and his photographer girlfriend (Forbes) traveling cross-country to research serial killings, who unwittingly carpool with a psychopath (Pitt) and his childlike girlfriend (Lewis).",
                "Kramer, American dramatic film, released in 1979, that tells the wrenching story of a divorce and custody battle from the point of view of the adults.",
                "The film stars Leonardo DiCaprio as a professional thief who steals information by infiltrating the subconscious of his targets.",
                "The plot revolves around Dylan Harper (Timberlake) and Jamie Rellis (Kunis), who meet in New York City, and naively believe adding sex to their friendship will not lead to complications."
        )
        val genres = listOf("Action", "Comedy", "Drama", "Fantasy", "Horror", "Science fiction")
        val images = listOf("https://upload.wikimedia.org/wikipedia/en/b/bc/Interstellar_film_poster.jpg",
                "https://upload.wikimedia.org/wikipedia/en/3/3b/Pulp_Fiction_%281994%29_poster.jpg",
                "https://upload.wikimedia.org/wikipedia/en/8/8d/Kaliforniaposter.jpg",
                "https://upload.wikimedia.org/wikipedia/en/thumb/b/b0/Oscar_posters_79.jpg/220px-Oscar_posters_79.jpg",
                "https://upload.wikimedia.org/wikipedia/en/2/2e/Inception_%282010%29_theatrical_poster.jpg",
                "https://upload.wikimedia.org/wikipedia/en/4/4e/Friends_with_benefits_poster.jpg")
        val durationTimes = listOf(110, 97, 116, 110, 115, 109)
        val releaseDates = listOf(LocalDate.of(2014, 10, 26),
                LocalDate.of(1993, 3, 27), LocalDate.of(2010, 7, 17),
                LocalDate.of(2004, 12, 10), LocalDate.of(2001, 2, 11),
                LocalDate.of(2011, 7, 22))
        val directors = listOf("Christopher Nolan", "Tom Bring", "Martin Backer", "Thomas Humber", "Stephan Lucky",
                "Will Gluck")

        for (i in 0..5) {
            movieService.addMovie(Movie(
                    title = titles[i],
                    description = descriptions[i],
                    genre = genres[i],
                    image = images[i],
                    durationTime = durationTimes[i],
                    releaseDate = releaseDates[i],
                    director = directors[i],
            ))
        }
        logger.info("Add movies successfully")
    }

    fun initScreeningRooms() {

        val names = listOf("Small hall", "Medium hall", "Big hall")
        val placeNumbers = listOf(38, 70, 141)
        val placePlan = listOf(listOf(
                "**********",
                "**********",
                " ******** ",
                " ******** ",
        ), listOf(
                "******   ******",
                "******   ******",
                "******   ******",
                "******   ******",
                "  ***********  ",
                "  ***********  "
        ), listOf(
                "****  ***************  ****",
                "****  ***************  ****",
                "****  ***************  ****",
                " ****  *************  **** ",
                "  ****  ***********  ****  ",
                "   ****  *********  ****   ",
                "    ****  *******  ****     "
        ))

        for (i in 0..2) {
            screeningRoomService.addScreeningRoom(ScreeningRoom(
                    name = names[i],
                    placeNumber = placeNumbers[i],
                    placesPlan = placePlan[i].toMutableList(),
            ))
        }
        logger.info("Add screening rooms successfully")
    }

    fun initSeances(seancesNumber: Int) {

        val hours = listOf(8, 10, 12, 14, 16, 18, 20)
        val movies = movieService.getAllMovies()
        val screeningRooms = screeningRoomService.getAllScreeningRooms()

        for (i in 0 until seancesNumber) {
            val movie = movies.random()
            val screeningRoom = screeningRooms.random()
            val seance = Seance(
                    startDate = LocalDate.of(2021, 3, (1.until(32)).toList().random()).atTime(LocalTime.of(hours.random(), 0)),
                    places = mutableListOf(),
                    movieId = movie.id,
                    screeningRoomId = screeningRoom.id
            )
            seanceService.addSeance(seance)
            movie.seancesIds.add(seance.id)
        }
        movies.map { movieService.addMovie(it) }
        screeningRooms.map { screeningRoomService.addScreeningRoom(it) }

        logger.info("Add seances successfully")
    }

    fun initPlaces() {

        val seances = seanceService.getAllSeances()

        for (i in seances.indices) {
            val screeningRoom = screeningRoomService.getScreeningRoomById(seances[i].screeningRoomId)
            for (j in 0 until screeningRoom.placeNumber) {
                val place = Place(
                        number = j + 1,
                        isReserved = listOf(true, false, false).random(),
                        seanceId = seances[i].id
                )
                placeService.addPlace(place)
                seances[i].places.add(place)
            }
        }
        seances.map { seanceService.addSeance(it) }

        logger.info("Add places successfully")
    }

    fun initUsers() {
        val usernames = listOf("goldenleopard404", "silverpeacock265", "lazydog489", "angrydog218", "redtiger294",
                "ticklishmeercat212", "angrydog814", "brownostrich913", "crazytiger516", "silverlion554",
                "silverpeacock515", "smallkoala475", "goldenmeercat577", "smallelephant997", "organicgorilla403",
                "goldenfrog112", "organicmeercat487", "greengorilla298", "saddog425", "yellowbear149", "happyrabbit749"
        )

        val leadingQuestions = listOf("What is your favourite color?",
                "Name of your best friend?",
                "Your pet\'s name?",
                "Model of your dream car?",
                "What is your favourite movie?"
        )

        val leadingAnswers = listOf("blue", "Stephan", "Baca", "Fiat", "hmm", "black", "door", "computer", "kitchen",
                "I don\'t know", "jacket", "towel", "bike", "glass", "tennis", "cola", "clock", "floor", "purple")

        for (element in usernames) {
            userService.addUser(User(
                    username = element,
                    leadingQuestion = leadingQuestions.random(),
                    leadingAnswer = leadingQuestions.random()
            ))
        }

        logger.info("Add users successfully")
    }

    fun initRates(ratesNumber: Int) {

        val users = userService.getAllUsers()
        val rates = listOf(5, 5, 5, 5, 5, 5, 5, 4, 4, 4, 3, 3, 3, 2, 2, 2, 1).map { it.toDouble() }
        val movies = movieService.getAllMovies()

        for (i in 0 until ratesNumber) {
            val user = users.random()
            val movie = movies.random()
            val rate = Rate(
                    user = user,
                    userRate = rates.random(),
                    movie = movie
            )
            rateService.addRate(rate)
            user.ratesIds.add(rate.id)
            movie.ratesIds.add(rate.id)
        }
        users.map { userService.addUser(it) }
        movies.map { movieService.addMovie(it) }

        logger.info("Add rates successfully")
    }

    fun initReservations() {

        val secretWords = listOf("rabbit", "pillow", "blouse", "bulb", "lamp", "glasses", "telephone", "newspaper",
                "backpack")

        val seances = seanceService.getAllSeances()
        val users = userService.getAllUsers()

        val booleanTable = listOf(false, false, false, true)

        for (element in seances) {
            val places = element.places
            var user = users.random()
            var userPlaces = mutableListOf<Place>()
            for (j in places.indices) {
                if (places[j].isReserved ) {
                    userPlaces.add(places[j])
                    if (booleanTable.random()) {
                        val reservation = Reservation(
                                user = user,
                                places = userPlaces,
                                secretWord = secretWords.random()
                        )
                        reservationService.addReservation(reservation)
                        user = users.random()
                        userPlaces.map { it.reservationId = reservation.id }
                        userPlaces.map { placeService.addPlace(it) }
                        user.reservationsIds.add(reservation.id)
                        userPlaces = mutableListOf()
                    }
                }
            }
            val reservation = Reservation(
                    user = user,
                    places = userPlaces,
                    secretWord = secretWords.random()
            )
            reservationService.addReservation(reservation)
            userPlaces.map { it.reservationId = reservation.id }
            userPlaces.map { placeService.addPlace(it) }
            user.reservationsIds.add(reservation.id)
        }
        users.map { userService.addUser(it) }

        logger.info("Add reservations successfully")
    }

    fun calculateRatesForEachFilm() {
        val movies = movieService.getAllMovies()

        for (i in movies.indices) {
            var sumOfRates = 0.0
            for (element in movies[i].ratesIds) {
                sumOfRates += rateService.getRateById(element).userRate
            }
            movies[i].rate = sumOfRates / movies[i].ratesIds.size
            movies[i].ratesNumber = movies[i].ratesIds.size
        }
        movies.map { movieService.addMovie(it) }

        logger.info("Calculate rates for each movie successfully")
    }

    override fun run(vararg args: String?) {
        initMovies()
        initScreeningRooms()
        initSeances(40)
        initPlaces()
        initUsers()
        initRates(500)
        initReservations()
        calculateRatesForEachFilm()
    }
}