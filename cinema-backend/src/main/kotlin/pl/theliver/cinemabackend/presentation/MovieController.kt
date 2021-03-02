package pl.theliver.cinemabackend.presentation

import org.springframework.web.bind.annotation.*
import pl.theliver.cinemabackend.application.services.MovieService
import pl.theliver.cinemabackend.application.services.SeanceService
import pl.theliver.cinemabackend.presentation.model.MovieDto
import pl.theliver.cinemabackend.presentation.model.SeanceDto

@RestController
@CrossOrigin
class MovieController(
        private val movieService: MovieService,
        private val seanceService: SeanceService
) {

    @PutMapping("/")
    fun process(): String {
//        listOf(
//            MovieDto(title = "Skazani na ", description = "Adaptacja opowiadania Stephena Kinga. Niesłusznie skazany na dożywocie bankier, stara się przetrwać w brutalnym, więziennym świecie.", image = "https://fwcdn.pl/fpo/10/48/1048/6925401.3.jpg", rate = 3.35, ratesNumber = 52, genre = "Komedia", durationTime = 274),
//            MovieDto(title = "Nietykalni", description = "Sparaliżowany milioner zatrudnia do opieki młodego chłopaka z przedmieścia, który właśnie wyszedł z więzienia.", image = "https://fwcdn.pl/fpo/10/48/1048/6925401.3.jpg", rate = 1.95, ratesNumber = 23, genre = "Thriller", durationTime = 73),
//            MovieDto(title = "Zielona mila", description = "Emerytowany strażnik więzienny opowiada przyjaciółce o niezwykłym mężczyźnie, którego skazano na śmierć za zabójstwo dwóch 9-letnich dziewczynek.", image = "https://fwcdn.pl/fpo/10/48/1048/6925401.3.jpg", rate = 2.62, ratesNumber = 15, genre = "Kryminał", durationTime = 220),
//            MovieDto(title = "Ojciec ", description = "Opowieść o nowojorskiej rodzinie mafijnej. Starzejący się Don Corleone pragnie przekazać władzę swojemu synowi.", image = "https://fwcdn.pl/fpo/10/48/1048/6925401.3.jpg", rate = 3.35, ratesNumber = 75, genre = "Komedia", durationTime = 120),
//            MovieDto(title = "Dwunastu  ", description = "Dwunastu przysięgłych ma wydać wyrok w procesie o morderstwo. Jeden z nich ma wątpliwości dotyczące winy oskarżonego.", image = "https://fwcdn.pl/fpo/10/48/1048/6925401.3.jpg", rate = 1.36, ratesNumber = 86, genre = "Komedia", durationTime = 170),
//            MovieDto(title = "Forrest Gump", description = "Historia życia Forresta, chłopca o niskim ilorazie inteligencji z niedowładem kończyn, który staje się miliarderem i bohaterem wojny w Wietnamie.", image = "https://fwcdn.pl/fpo/10/48/1048/6925401.3.jpg", rate = 2.43, ratesNumber = 36, genre = "Przygodowy", durationTime = 153),
//            MovieDto(title = "Lot nad  ", description = "Historia złodzieja, szulera i chuligana, który, by uniknąć więzienia, udaje niepoczytalność. Trafia do szpitala dla umysłowo chorych, gdzie twardą ręką rządzi siostra Ratched.", image = "https://fwcdn.pl/fpo/10/48/1048/6925401.3.jpg", rate = 4.74, ratesNumber = 86, genre = "Przygodowy", durationTime = 136),
//            MovieDto(title = "Ojciec  II", description = "Rok 1917. Młody Vito Corleone stawia pierwsze kroki w mafijnym świecie Nowego Jorku. Ponad 40 lat później jego syn Michael walczy o interesy i dobro rodziny.", image = "https://fwcdn.pl/fpo/10/48/1048/6925401.3.jpg", rate = 3.74, ratesNumber = 26, genre = "Komedia", durationTime = 156),
//            MovieDto(title = "Władca a", description = "Zwieńczenie filmowej trylogii wg powieści Tolkiena. Aragorn jednoczy siły Śródziemia, szykując się do bitwy, która ma odwrócić uwagę Saurona od podążających w kierunku Góry Przeznaczenia hobbitów.", image = "https://fwcdn.pl/fpo/10/48/1048/6925401.3.jpg", rate = 1.25, ratesNumber = 59, genre = "Thriller", durationTime = 125),
//            MovieDto(title = "Lista ", description = "Historia przedsiębiorcy Oskara Schindlera, który podczas II wojny światowej uratował przed pobytem w obozach koncentracyjnych 1100 Żydów.", image = "https://fwcdn.pl/fpo/10/48/1048/6925401.3.jpg", rate = 3.5, ratesNumber = 2, genre = "Thriller", durationTime = 12
//            )
//        ).map { it.toDomain() }
//            .forEach { movieService.addMovie(it) }

        return "Add data successfully"
    }

    @GetMapping("/movies")
    fun getAllMovies() = movieService.getAllMovies()


    @GetMapping("/movies/{id}")
    fun getMovie(@PathVariable("id") id: String) = movieService.getMovieById(id)

    @GetMapping("/movies/{id}/seances")
    fun getSeancesFromMovie(@PathVariable("id") id: String) =
            seanceService.getSeancesFromMovie(id).map { SeanceDto.fromDomain(it) }
}