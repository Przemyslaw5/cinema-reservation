import { Component, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Movie } from 'src/app/model/movie';
import { NewSeance } from 'src/app/model/newSeance';
import { Seance } from 'src/app/model/seance';
import { SeanceValidation } from 'src/app/model/seanceValidation';
import { CinemaService } from 'src/app/services/cinema.service';

@Component({
  selector: 'app-add-or-edit-seances',
  templateUrl: './add-or-edit-seances.component.html',
  styleUrls: ['./add-or-edit-seances.component.scss']
})

export class AddOrEditSeancesComponent implements OnInit {

  movie?: Movie
  newSeance?: NewSeance
  validations: SeanceValidation[] = []
  seancesFromMovie: Seance[] = []

  modelForm = new FormGroup({
    seances: new FormArray([
    ]),
  })

  get seances(): FormArray {
    return this.modelForm.get('seances') as FormArray;
  }

  allScreeningRoomOptions: string[] = []

  constructor(
    private cinemaService: CinemaService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.getMovieFromUrl()
    this.getAllScreeningRooms()
  }

  loadSeancesFromMovie() {
    console.log(this.movie!.id)
    this.cinemaService.getSeancesFromMovieId(this.movie!.id).subscribe(seances => {
      this.seancesFromMovie = seances;
      this.seancesFromMovie.map(seance => {
        (<FormArray>this.modelForm.get('seances')).push(new FormGroup({
          time: new FormControl(new Date(seance.startDate)),
          screeningRoom: new FormControl(seance.screeningRoomName, [Validators.required])
        }))
        this.validations.push({
          screeningRoomInvalid: false,
          screeningRoomError: "Screening room is required",
          hourInvalid: false,
          hourError: "There is a conflict with another seance. Please choose a different time"
        })
      })
      this.addAnotherSeance()
    });
  }

  checkAddingNewSeance() {

    const index = (<FormArray>this.modelForm.get('seances')).length - 1

    const seanceForm = (<FormArray>this.modelForm.get('seances')).at((index))
    const screeningRoom = seanceForm.get('screeningRoom')!.value
    const startDate = seanceForm.get('time')!.value

    if (screeningRoom == "") {
      this.validations[index].screeningRoomInvalid = true
    }
    else { 
      this.prepareNewSeance(screeningRoom, startDate) 
      this.cinemaService.addNewSeance(this.newSeance!).subscribe(value => {
        if (value == null) {
          this.validations[index].screeningRoomInvalid = false
          this.validations[index].hourInvalid = true
        }
        else {
          this.validations[index].hourInvalid = false
          this.seancesFromMovie.push(value)
          this.addAnotherSeance()
        }
      }
      , error => {
        console.log(error)
      });
    }
  } 

  prepareNewSeance(screeningRoom: string, startDate: Date) {
    this.newSeance = {
      movieId: this.movie!.id,
      startDate: startDate.toISOString(),
      screeningRoomName: screeningRoom

    }
  }

  addAnotherSeance() {
    (<FormArray>this.modelForm.get('seances')).push(new FormGroup({
      time: new FormControl(new Date()),
      screeningRoom: new FormControl('', [Validators.required])
    }))

    this.validations.push({
      screeningRoomInvalid: false,
      screeningRoomError: "Screening room is required",
      hourInvalid: false,
      hourError: "There is a conflict with another seance. Please choose a different time"
    })
  }

  getMovieFromUrl() {
    const id = this.route.snapshot.paramMap.get("id");
      this.cinemaService.getMovieById(id!).subscribe(
        movie => {
          this.movie = movie
          this.loadSeancesFromMovie()
        },
        ({error}) =>
          console.log(error)
      );
  }

  getAllScreeningRooms() {
    console.log("ESA")
    this.cinemaService.getAllScreeningRooms().subscribe(rooms => {
      this.allScreeningRoomOptions = rooms.map(room => room.name)
      console.log(this.allScreeningRoomOptions)
    });
  }

  public deleteSeance(index: number) {
    this.validations.splice(index, 1);
    (<FormArray>this.modelForm.get('seances')).removeAt(index)
    this.cinemaService.deleteSeance(this.seancesFromMovie[index].id)
    this.seancesFromMovie.splice(index, 1)
  }

}
