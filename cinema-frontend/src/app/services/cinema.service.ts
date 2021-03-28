import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Movie } from '../model/movie';
import { NewMovie } from '../model/newMovie';
import { NewSeance } from '../model/newSeance';
import { Place } from '../model/place';
import { ScreeningRoom } from '../model/screeningRoom';
import { Seance } from '../model/seance';

@Injectable({
  providedIn: 'root'
})

export class CinemaService {

  DOMAIN = environment.apiBase;

  constructor(
    private httpClient: HttpClient
  ) { }

  public getMovies(): Observable<Movie[]> {
    return this.httpClient.get<Movie[]>(this.DOMAIN + `/movies`)
  }

  public getMovieById(id: string): Observable<Movie> {
    return this.httpClient.get<Movie>(this.DOMAIN + `/movies/${id}`);
  }

  public getScreeningRoom(id: string): Observable<ScreeningRoom> {
    return this.httpClient.get<ScreeningRoom>(this.DOMAIN + `/screening-rooms/${id}`)
  }

  public getAllScreeningRooms(): Observable<ScreeningRoom[]> {
    return this.httpClient.get<ScreeningRoom[]>(this.DOMAIN + `/screening-rooms`)
  }

  public getAllGenres(): Observable<string[]> {
    return this.httpClient.get<string[]>(this.DOMAIN + `/genres`)
  }

  public addNewMovie(newMovie: NewMovie): Observable<boolean> {
    return this.httpClient.post<boolean>(this.DOMAIN + `/movie/add`, newMovie)
  }

}
