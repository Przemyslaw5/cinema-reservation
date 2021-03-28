import { Component, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Movie } from 'src/app/model/movie';
import { NewSeance } from 'src/app/model/newSeance';
import { Seance } from 'src/app/model/seance';
import { CinemaService } from 'src/app/services/cinema.service';
import { SeanceService } from 'src/app/services/seance.service';

@Component({
  selector: 'app-add-or-edit-seances',
  templateUrl: './add-or-edit-seances.component.html',
  styleUrls: ['./add-or-edit-seances.component.scss']
})

export class AddOrEditSeancesComponent implements OnInit {

  movie?: Movie
  newSeance?: NewSeance
  seancesFromMovie: Seance[] = []

  timeConflict          = false;
  screeningRoomConflict = false;

  modelForm = new FormGroup({
    seances: new FormArray([]),
  })

  get seances(): FormArray {
    return this.modelForm.get('seances') as FormArray;
  }

  allScreeningRoomOptions: string[] = []

  constructor(
    private cinemaService: CinemaService,
    private seanceService: SeanceService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.getMovieFromUrl()
    this.getAllScreeningRooms()
  }

  private loadSeancesFromMovie() {
    this.seanceService.getSeancesFromMovieId(this.movie!.id).subscribe(seances => {
      this.seancesFromMovie = seances;
      this.seancesFromMovie.map(seance => {
        (<FormArray>this.modelForm.get('seances')).push(new FormGroup({
          date: new FormControl(new Date(seance.startDate)),
          time: new FormControl(new Date(seance.startDate)),
          screeningRoom: new FormControl(seance.screeningRoomName, [Validators.required])
        }))

        this.timeConflict = false
        this.screeningRoomConflict = false;

      })
      this.addAnotherSeance()
    });
  }

  public checkAddingNewSeance() {
    const index = (<FormArray>this.modelForm.get('seances')).length - 1

    const seanceForm = (<FormArray>this.modelForm.get('seances')).at((index))
    const screeningRoom = seanceForm.get('screeningRoom')!.value
    const startDate = seanceForm.get('date')!.value
    const startTime = seanceForm.get('time')!.value

    if (screeningRoom == "" || startDate == null || startTime == null) {
      this.screeningRoomConflict = true
    }
    else { 
      this.addNewSeance(screeningRoom, startDate, startTime, index)
    }
  } 

  private addNewSeance(screeningRoom: string, startDate: Date, startTime: string, index: number) {
    this.prepareNewSeance(screeningRoom, startDate, startTime) 
    this.seanceService.addNewSeance(this.newSeance!).subscribe(value => {
      if (value == null) {
        this.screeningRoomConflict = false
        this.timeConflict = true
      }
      else {
        this.timeConflict = false;

        (<FormArray>this.modelForm.get('seances')).removeAt(index);

        (<FormArray>this.modelForm.get('seances')).push(new FormGroup({
          date: new FormControl(new Date(value.startDate)),
          time: new FormControl(new Date(value.startDate)),
          screeningRoom: new FormControl(value.screeningRoomName, [Validators.required])
        }))

        this.seancesFromMovie.push(value)
        this.addAnotherSeance()
      }
    }
    , error => {
      console.log(error)
    });
  }

  private prepareNewSeance(screeningRoom: string, startDate: Date, startTime: string) {
    const date = new Date(startDate)
    date.setHours(parseInt(startTime.split(":")[0]))
    date.setMinutes(parseInt(startTime.split(":")[1]))
    this.newSeance = {
      movieId: this.movie!.id,
      startDate: date.toISOString(),
      screeningRoomName: screeningRoom

    }
  }

  private addAnotherSeance() {
    (<FormArray>this.modelForm.get('seances')).push(new FormGroup({
      date: new FormControl(),
      time: new FormControl(),
      screeningRoom: new FormControl('', [Validators.required])
    }))

    this.timeConflict = false
    this.screeningRoomConflict = false;
  }

  private getMovieFromUrl() {
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

  private getAllScreeningRooms() {
    this.cinemaService.getAllScreeningRooms().subscribe(rooms => {
      this.allScreeningRoomOptions = rooms.map(room => room.name)
    });
  }

  public deleteSeance(index: number) {
    (<FormArray>this.modelForm.get('seances')).removeAt(index)
    this.seanceService.deleteSeance(this.seancesFromMovie[index].id)
    this.seancesFromMovie.splice(index, 1)
  }

  public getDate(index: number) {
    const date = new Date((<FormArray>this.modelForm.get('seances')).at((index)).get('time')!.value)
    return date.getMonth() + 1 + "/" + date.getDate() + "/" + date.getFullYear()
  }

  public getTime(index: number) {
    const date = new Date((<FormArray>this.modelForm.get('seances')).at((index)).get('time')!.value)
    return (date.getHours() + ":" + (date.getMinutes()<10?'0':'') + date.getMinutes())
  }

}
