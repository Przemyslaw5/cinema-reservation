import { Component, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Movie } from 'src/app/model/movie';
import { NewSeance } from 'src/app/model/newSeance';
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
    this.addAnotherSeance()
  }

  checkAddingNewSeance() {

    const index = (<FormArray>this.modelForm.get('seances')).length - 1

    const seanceForm = (<FormArray>this.modelForm.get('seances')).at((index))
    console.log("<AS")
    const screeningRoom = seanceForm.get('screeningRoom')!.value
    console.log("SA")
    const startDate = seanceForm.get('time')!.value
    console.log("EASE")

    if (screeningRoom == "") {
      console.log("ESA")
      this.validations[index].screeningRoomInvalid = true
    }
    else { 
      this.prepareSeance(screeningRoom, startDate) 
      this.cinemaService.addNewSeance(this.newSeance!).subscribe(value => {
        console.log(value)
        if (!value) {
          this.validations[index].screeningRoomInvalid = false
          this.validations[index].hourInvalid = true
        }
        else {
          this.validations[index].hourInvalid = false
          this.addAnotherSeance()
        }
      }
      , error => {
        console.log(error)
      });
    }
  } 

  prepareSeance(screeningRoom: string, startDate: Date) {
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

}
