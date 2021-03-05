import { Component, OnInit } from '@angular/core';
import { Options } from '@angular-slider/ngx-slider';
import { FilterService } from 'src/app/services/filter.service';
import { MovieFilterData } from 'src/app/pipes/filter-movie.pipe';
import { CinemaService } from 'src/app/services/cinema.service';

@Component({
  selector: 'app-filter-movie',
  templateUrl: './filter-movie.component.html',
  styleUrls: ['./filter-movie.component.scss']
})
export class FilterMovieComponent implements OnInit {

  allOptions: string[] = []

  filterObject: MovieFilterData = {
    title: "",
    genre: "",
    rate: {min: 0, max: 5},
    durationTime: {min: 60, max: 250}
  }

  constructor(
    private filterService: FilterService,
    private cinemaService: CinemaService
  ) { }

  ngOnInit(): void {
    this.getAllGenres()
  }

  rateOptions: Options = {
    floor: 0,
    ceil: 5,
    animate: false,
    step: 0.01
  };

  durationTimeOptions: Options = {
    floor: 60,
    ceil: 250,
    animate: false,
    step: 1
  };

  translate(text: string): string {
    return text.replace( /([A-Z])/g, " $1" ).slice(1);
  }

  getAllGenres() {
    this.cinemaService.getAllGenres().subscribe(genres => {
      this.allOptions = genres.map(genre => genre.replace("_", " "));
    });
  }

  clear() {
    this.filterObject.title = "";
    this.filterObject.genre = "";
    this.filterObject.rate = {min: 0, max: 5};
    this.filterObject.durationTime = {min: 60, max: 250};
    this.sendFilters();
  }

  sendFilters() : void {
    console.log(this.filterObject.title)
    this.filterService.updateFilters(this.filterObject);
  }

}
