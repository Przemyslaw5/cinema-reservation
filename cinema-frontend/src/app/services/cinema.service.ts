import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Movie } from '../model/movie';

@Injectable({
  providedIn: 'root'
})

export class CinemaService {

  DOMAIN = environment.apiBase;

  constructor(
    private httpClient: HttpClient
  ) { }

  getMovies(): Observable<Movie[]> {
    return this.httpClient.get<Movie[]>(this.DOMAIN + '/movies')
  }

  getMovieById(id: string) {
    return this.httpClient.get<Movie>(this.DOMAIN + `/movies/${id}`);
  }

}
