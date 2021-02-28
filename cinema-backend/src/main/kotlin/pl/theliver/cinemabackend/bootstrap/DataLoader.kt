package pl.theliver.cinemabackend.bootstrap

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import pl.theliver.cinemabackend.application.repositories.*
import pl.theliver.cinemabackend.domain.Movie
import pl.theliver.cinemabackend.domain.ScreeningRoom
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.*
import pl.theliver.cinemabackend.infrastructure.model.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*
import javax.persistence.*
import javax.transaction.Transactional

@Component
@Transactional
class DataLoader(
        private val movieRepository: MovieRepository,
        private val placeRepository: PlaceRepository,
        private val rateRepository: RateRepository,
        private val reservationRepository: ReservationRepository,
        private val screeningRoomRepository: ScreeningRoomRepository,
        private val seanceRepository: SeanceRepository,
        private val userRepository: UserRepository,
        private val movieCrudRepositoryJpa: MovieCrudRepositoryJpa,
        private val placeCrudRepositoryJpa: PlaceCrudRepositoryJpa,
        private val rateCrudRepositoryJpa: RateCrudRepositoryJpa,
        private val reservationCrudRepositoryJpa: ReservationCrudRepositoryJpa,
        private val screeningRoomCrudRepositoryJpa: ScreeningRoomCrudRepositoryJpa,
        private val seanceCrudRepositoryJpa: SeanceCrudRepositoryJpa,
        private val userCrudRepositoryJpa: UserCrudRepositoryJpa
) : CommandLineRunner {

    fun initMovies() {
        val titles = listOf("Interstellar", "Pulp Fiction", "Kalifornia",
                "Kramer Vs. Kramer", "Inception", "Friends With Benefits")
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
        val rates = listOf(4.22857, 4.07692, 3.99556, 2.8125, 3.95256, 4.904)
        val ratesNumbers = listOf(35, 26, 676, 32, 253, 125)
        val durationTimes = listOf(169, 225, 125, 129, 173, 109)
//        val seancesIds: List<String>,
        val releaseDates = listOf(LocalDate.of(2014, 10, 26),
                LocalDate.of(1993, 3, 27), LocalDate.of(2010, 7, 17),
                LocalDate.of(2004, 12, 10), LocalDate.of(2001, 2, 11),
                LocalDate.of(2011, 7, 22))
        val directors = listOf("Christopher Nolan", "Tom Bring", "Martin Backer",
                "Thomas Humber", "Stephan Lucky", "Will Gluck")
//        val ratesIds: List<String>


//        val id: String = UUID.randomUUID().toString(),
//        val title: String,
//        val description: String,
//        val genre: String,
//        val image: String,
//        var rate: Double,
//        val ratesNumber: Int,
//        val durationTime: Int,
//        @OneToMany(mappedBy = "movie")
//        var seances: List<SeanceEntity>,
//        val releaseDate: LocalDate,
//        val director: String,
//        @OneToMany(mappedBy = "movie")
//        var rates: List<RateEntity>

        for (i in 0..5) {
            movieRepository.saveMovie(MovieEntity(
                    title = titles[i],
                    description = descriptions[i],
                    genre = genres[i],
                    image = images[i],
                    rate = rates[i],
                    ratesNumber = ratesNumbers[i],
                    durationTime = durationTimes[i],
                    seances = emptyList(),
                    releaseDate = releaseDates[i],
                    director = directors[i],
                    rates = emptyList()
            ).toDomain())
        }
    }

    fun initScreeningRooms() {

//        val id: String = UUID.randomUUID().toString(),
//        val name: String,
//        val placeNumber: Int,
//        @ElementCollection
//        var placesPlan: List<String>,
//        @OneToMany(mappedBy="screeningRoom")
//        var seances: List<SeanceEntity>

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
            screeningRoomRepository.saveScreeningRoom(ScreeningRoom(
                    name = names[i],
                    placeNumber = placeNumbers[i],
                    placesPlan = placePlan[i],
                    seancesIds = emptyList()
            ))
        }
    }

    fun initSeances() {
//        val id: String = UUID.randomUUID().toString(),
//        val startDate: LocalDateTime,
//        @OneToMany(mappedBy = "seance", fetch = FetchType.LAZY)
//        val places: List<PlaceEntity>,
//        @ManyToOne
//        @JoinColumn(name = "movie_id")
//        val movie: MovieEntity,
//        @ManyToOne
//        @JoinColumn(name = "screening_room_id")
//        var screeningRoom: ScreeningRoomEntity,


        val startDates = listOf(LocalDate.of(2021, 3, 2).atTime(LocalTime.of(12, 0)),
                LocalDate.of(2021, 3, 2).atTime(LocalTime.of(15, 0)),
                LocalDate.of(2021, 3, 2).atTime(LocalTime.of(18, 0))
        )
//        val places = listOf()
//        val movies = listOf()
//        val screeningRooms = listOf()
//        val movies = movieRepository.getAllMovies()
//        val screeningRooms = screeningRoomRepository.getAllScreeningRooms()

//        for (i in 0..3) {
//            seanceRepository.saveSeance(SeanceEntity(
//                    startDate = startDates[i],
//                    places = emptyList(),
//                    movie = MovieEntity.fromDomain(movies[0], seanceCrudRepositoryJpa, rateCrudRepositoryJpa),
//                    screeningRoom = ScreeningRoomEntity.fromDomain(screeningRooms[0], seanceCrudRepositoryJpa)
//            ).toDomain())
//        }
    }

    fun initPlaces() {

//        val id: String = UUID.randomUUID().toString(),
//        val number: Int,
//        var isReserved: Boolean,
//        @ManyToOne
//        @JoinColumn(name = "seance_id")
//        val seance: SeanceEntity,
//        @ManyToOne
//        @JoinColumn(name = "reservation_id")
//        val reservation: ReservationEntity
    }

    fun initRates() {
//        val id: String = UUID.randomUUID().toString(),
//        @ManyToOne
//        @JoinColumn(name = "user_id")
//        val user: UserEntity,
//        val userRate: Double,
//        @ManyToOne
//        @JoinColumn(name = "movie_id")
//        val movie: MovieEntity
    }

    fun initReservations() {
//        val id: String = UUID.randomUUID().toString(),
//        @ManyToOne
//        @JoinColumn(name = "user_id")
//        var user: UserEntity,
//        @OneToMany(mappedBy = "reservation")
//        var places: List<PlaceEntity>,
//        var secretWord: String
    }

    fun initUsers() {
//        val id: String = UUID.randomUUID().toString(),
//        val username: String,
//        val leadingQuestion: String,
//        val leadingAnswer: String,
//        @OneToMany(mappedBy = "user")
//        val reservations: List<ReservationEntity>,
//        @OneToMany(mappedBy = "user")
//        val rates: List<RateEntity>
    }

    override fun run(vararg args: String?) {
        initMovies()
        initScreeningRooms()
        initSeances()
    }
}