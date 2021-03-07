import { Component, OnInit } from '@angular/core';
import { Movie } from 'src/app/model/movie';
import { ActivatedRoute } from '@angular/router';
import { CinemaService } from 'src/app/services/cinema.service';
import { Place } from 'src/app/model/place';
import { Seance } from 'src/app/model/seance';
import { ScreeningRoom } from 'src/app/model/screeningRoom';
import { ReservedType } from 'src/app/model/reservedType';
import { ReservationDate } from 'src/app/model/reservationDate';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.scss']
})
export class ReservationComponent implements OnInit {

  movie!: Movie;

  allSeancesFromMovie!: Seance[];

  yearOptions: string[] = [];
  monthOptions: string[] = [];
  dayOptions: string[] = [];
  hourOptions: string[] = [];

  reservationDate: ReservationDate = {
    year: "",
    month: "",
    day: "",
    hour: ""
  }
  

  screeningRoom!: ScreeningRoom;
  placesFromSeance!: Place[];

  placefForTemplate: Place[][] = [[]]

  constructor(
    private cinemaService: CinemaService,
    private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    this.getMovieFromUrl();
  }

  public getMovieFromUrl(){
    const id = this.route.snapshot.paramMap.get("id");
    if (id != null){
      this.cinemaService.getMovieById(id).subscribe(movie =>  {
        this.movie = movie;
        this.getAllSeancesFromMovie()
      });
    }
  }

  private getAllSeancesFromMovie(){
    this.cinemaService.getSeancesFromMovieId(this.movie.id).subscribe(seances => {
      this.allSeancesFromMovie = seances;
      this.prepareYears()
    });
  }

  public prepareYears() {
    this.yearOptions = this.allSeancesFromMovie.map(seance => seance.startDate.split("-")[0]).filter((value, index, self) => self.indexOf(value) === index).sort()
    if (this.yearOptions.length == 1) {
      this.reservationDate.year = this.yearOptions[0]
      this.prepareMonths()
    }
  }

  public prepareMonths() {
    this.reservationDate.month = "";
    this.reservationDate.day = "";
    this.reservationDate.hour = "";
    this.dayOptions = [];
    this.hourOptions = [];

    this.monthOptions = this.allSeancesFromMovie.filter(seance => seance.startDate.split("-")[0] == this.reservationDate.year).map(seance => seance.startDate.split("-")[1]).filter((value, index, self) => self.indexOf(value) === index).sort()

    if (this.monthOptions.length == 1) {
      this.reservationDate.month = this.monthOptions[0]
      this.prepareDays()
    }
  }

  public prepareDays() {
    this.reservationDate.day = "";
    this.reservationDate.hour = "";
    this.hourOptions = [];

    this.dayOptions = this.allSeancesFromMovie.filter(seance => seance.startDate.split("-")[0] == this.reservationDate.year && seance.startDate.split("-")[1] == this.reservationDate.month).map(seance => seance.startDate.split("-")[2].split('T')[0]).filter((value, index, self) => self.indexOf(value) === index).sort()

    if (this.dayOptions.length == 1) {
      this.reservationDate.day = this.dayOptions[0]
      this.prepareHours()
    }
  }

  public prepareHours() {
    this.reservationDate.hour = "";
    
    this.hourOptions = this.allSeancesFromMovie.filter(seance => seance.startDate.split("-")[0] == this.reservationDate.year && seance.startDate.split("-")[1] == this.reservationDate.month && seance.startDate.split("-")[2].split("T")[0] == this.reservationDate.day).map(seance => seance.startDate.split("-")[2].split("T")[1].split(":")[0] + ':' + seance.startDate.split(":")[1] + ' ' + seance.screeningRoomName).sort()
    
    if (this.hourOptions.length == 1) {
      this.reservationDate.hour = this.hourOptions[0]
      this.findSeance()
    }
  }

  public findSeance(){
    var hour = this.reservationDate.hour.split(':')[0]
    var min = this.reservationDate.hour.split(':')[1].split(' ')[0]
    var name = this.reservationDate.hour.substring(6)

    var seance = this.allSeancesFromMovie.filter(seance => seance.startDate.split("-")[0] == this.reservationDate.year && seance.startDate.split("-")[1] == this.reservationDate.month && seance.startDate.split("-")[2].split("T")[0] == this.reservationDate.day && seance.startDate.split("-")[2].split("T")[1].split(":")[0] == hour && seance.startDate.split(":")[1] == min && seance.screeningRoomName == name)[0]

    this.getScreeningRoomAndPreparePlaces(seance.screeningRoomId, seance.id)
  }

  private getScreeningRoomAndPreparePlaces(screeningRoomId: string, seanceId: string) {
    this.cinemaService.getScreeningRoom(screeningRoomId).subscribe(screeningRoom => {
      this.screeningRoom = screeningRoom;
      this.getPlaces(seanceId)
    });
  }

  private getPlaces(seanceId: string) {
    this.cinemaService.getPlacesFromSeanceId(seanceId).subscribe(places => {
      this.placesFromSeance = places;
      this.preparePlaces()
    });
  }

  private preparePlaces() {
    this.placefForTemplate = []
    var place = 0;
    for (let i = 0; i < this.screeningRoom.placesPlan.length; i++) {
      let row: Place[] = []
      for (let j = 0; j < this.screeningRoom.placesPlan[i].length; j++) {
        if (this.screeningRoom.placesPlan[i][j] == '*') {
          row.push({ id: this.placesFromSeance[place].id, number: this.placesFromSeance[place].number, isReserved: this.placesFromSeance[place].isReserved })
          place++
        }
        else {
          row.push({ id: "", number: ' ', isReserved: ReservedType.RESERVED })
        }
      }
      this.placefForTemplate.push(row)
    }
  }

  public clickSeat(row: number, seat: number) {
    if (this.placefForTemplate[row][seat].isReserved == ReservedType.FREE){
      this.placefForTemplate[row][seat].isReserved = ReservedType.RESERVED_BY_ME;
    }
    else if (this.placefForTemplate[row][seat].isReserved == ReservedType.RESERVED_BY_ME){
      this.placefForTemplate[row][seat].isReserved = ReservedType.FREE;
    }
  }

}
