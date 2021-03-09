import { Options } from '@angular-slider/ngx-slider';
import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { NewMovie } from 'src/app/model/newMovie';
import { CinemaService } from 'src/app/services/cinema.service';

@Component({
  selector: 'app-add-or-edit-movie',
  templateUrl: './add-or-edit-movie.component.html',
  styleUrls: ['./add-or-edit-movie.component.scss']
})
export class AddOrEditMovieComponent implements OnInit {

  modelForm = new FormGroup({
    title: new FormControl('', [Validators.required]),
    description: new FormControl('', [Validators.required, Validators.minLength(20)]),
    genre: new FormControl('', [Validators.required]),
    image: new FormControl('', [Validators.required, Validators.pattern("https?:[/|.|\\w|\\s|-]*\\.(?:jpg|gif|png|jpeg).*")]),
    durationTime: new FormControl(120),
    releaseDate: new FormControl(new Date),
    director: new FormControl('', [Validators.required]),
  })
  
  allGenresOptions: string[] = []

  durationTimeOptions: Options = {
    floor: 60,
    ceil: 250, 
    animate: false,
    step: 1
  };

  constructor(
    private cinemaService: CinemaService
  ) { }

  ngOnInit(): void {
    this.getAllGenres()
  }

  getAllGenres() {
    this.cinemaService.getAllGenres().subscribe(genres => {
      this.allGenresOptions = genres.map(genre => genre.replace("_", " "));
    });
  }

  addNewMovie() {
    this.cinemaService.addNewMovie(this.modelForm.value!).subscribe(value => {
      value ? this.modelForm.controls['title'].setErrors(null) : this.modelForm.controls['title'].setErrors({'unique': !value})
    }
    , error => {
      console.log(error)
    });
  }

  addAndNaviagteToSeances() {
    this.cinemaService.addNewMovie(this.modelForm.value!).subscribe(value => {
      value ? this.modelForm.controls['title'].setErrors(null) : this.modelForm.controls['title'].setErrors({'unique': !value})
    }
    , error => {
      console.log(error)
    });
  }

}