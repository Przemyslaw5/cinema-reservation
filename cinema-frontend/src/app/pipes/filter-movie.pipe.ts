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
      movies = movies.filter(movie => 
        movie.title.toUpperCase().includes(filter.title.toUpperCase())
      );
    }

    if (filter.genre){
      movies = movies.filter(movie => 
        movie.genre == filter.genre
      );
    }

    return movies;

  }

}
