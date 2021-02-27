import { Component, OnInit } from '@angular/core';
import { Movie } from 'src/app/model/movie';
import { MovieFilterData } from 'src/app/pipes/filter-movie.pipe';
import { CinemaService } from 'src/app/services/cinema.service';
import { FilterService } from 'src/app/services/filter.service';

@Component({
  selector: 'app-movie-list',
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.scss']
})
export class MovieListComponent implements OnInit {

  movies: Movie[] = [];

  filterObject!: MovieFilterData;

  constructor(
    private cinemaService: CinemaService,
    private filterService: FilterService
  ) { }

  ngOnInit(): void {
    this.getMovies();
    this.getFilters();
  }

  getMovies() {
    this.cinemaService.getMovies().subscribe(movies => {
      this.movies = movies;
    });
  }

  getFilters(){
    this.filterService.getActualFilters()
      .subscribe(filterObject => this.filterObject = filterObject);
  }

}
