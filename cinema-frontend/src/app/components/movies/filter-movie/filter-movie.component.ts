import { Component, OnInit } from '@angular/core';
import { Options } from '@angular-slider/ngx-slider';
import { FilterService } from 'src/app/services/filter.service';
import { MovieFilterData } from 'src/app/pipes/filter-movie.pipe';
import { MovieGenre } from 'src/app/model/movie';

@Component({
  selector: 'app-filter-movie',
  templateUrl: './filter-movie.component.html',
  styleUrls: ['./filter-movie.component.scss']
})
export class FilterMovieComponent implements OnInit {

  emptyGenre = MovieGenre.Empty;

  reset = true;

  allOptions = Object.keys(MovieGenre).filter(k => typeof MovieGenre[k as any] === "number").slice(1);

  filterObject: MovieFilterData = {
    title: "",
    genre: MovieGenre.Empty,
    rate: {min: 0, max: 5},
    durationTime: {min: 60, max: 250}
  }

  constructor(
    private filterService: FilterService
  ) { }

  ngOnInit(): void {
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

  clear() {
    this.filterObject.title = "";
    this.filterObject.genre = MovieGenre.Empty;
    this.filterObject.rate = {min: 0, max: 5};
    this.filterObject.durationTime = {min: 60, max: 250};
    this.sendFilters();
  }

  sendFilters() : void {
    console.log(this.filterObject.title)
    this.filterService.updateFilters(this.filterObject);
  }

}
