import { Pipe, PipeTransform } from '@angular/core';
import { Movie, MovieGenre } from '../model/movie';

export interface Range {
  min: number;
  max: number;
}

export interface MovieFilterData {
  title: string;
  rate: Range;
  genre: MovieGenre;
  durationTime: Range;
}

@Pipe({
  name: 'filterMovie',
  pure: false
})
export class FilterMoviePipe implements PipeTransform {

  transform(movies: Movie[], filter: MovieFilterData): Movie[] {
    if (!movies) {
      return [];
    }

    if (!filter) {
      return movies;
    }

    if (filter.title){
      movies = movies.filter(movie => movie.title.toUpperCase().includes(filter.title.toUpperCase()));
    }

    if (filter.genre && filter.genre && filter.genre.toString() != 'All'){
        movies = movies.filter(movie => movie.genre == filter.genre);
    }

    if (filter.rate){
      movies = movies.filter(movie => filter.rate.min <= movie.rate && movie.rate <= filter.rate.max)
    }

    if (filter.durationTime){
      movies = movies.filter(movie => filter.durationTime.min <= movie.durationTime && movie.durationTime <= filter.durationTime.max)
    }

    return movies;
  }
}
