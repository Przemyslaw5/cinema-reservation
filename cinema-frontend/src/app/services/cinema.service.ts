import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Movie } from '../model/movie';
import { NewMovie } from '../model/newMovie';
import { NewReservation } from '../model/newReservation';
import { NewSeance } from '../model/newSeance';
import { Place } from '../model/place';
import { ScreeningRoom } from '../model/screeningRoom';
import { Seance } from '../model/seance';
import { UserReservation } from '../model/userReservation';

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

  public getSeancesFromMovieId(id: string): Observable<Seance[]> {
    return this.httpClient.get<Seance[]>(this.DOMAIN + `/movies/${id}/seances`);
  }

  public getScreeningRoom(id: string): Observable<ScreeningRoom> {
    return this.httpClient.get<ScreeningRoom>(this.DOMAIN + `/screening-rooms/${id}`)
  }

  public getAllScreeningRooms(): Observable<ScreeningRoom[]> {
    return this.httpClient.get<ScreeningRoom[]>(this.DOMAIN + `/screening-rooms`)
  }

  public getPlacesFromSeanceId(seanceId: string): Observable<Place[]> {
    return this.httpClient.get<Place[]>(this.DOMAIN + `/screening-rooms/${seanceId}/places`)
  }

  public getAllGenres(): Observable<string[]> {
    return this.httpClient.get<string[]>(this.DOMAIN + `/genres`)
  }

  public getAllReservationsForUser(username: string): Observable<UserReservation[]> {
    return this.httpClient.post<UserReservation[]>(this.DOMAIN + `/reservations`, username)
  }

  public addNewMovie(newMovie: NewMovie): Observable<boolean> {
    return this.httpClient.post<boolean>(this.DOMAIN + `/movie/add`, newMovie)
  }

  public addNewSeance(newSeance: NewSeance): Observable<Seance> {
    return this.httpClient.post<Seance>(this.DOMAIN + `/seance/add`, newSeance)
  }

  public deleteSeance(id: string) {
    return this.httpClient.delete(this.DOMAIN + `/remove/seance/${id}`)
  }

  public addNewReservation(newReservation: NewReservation): Observable<boolean> {
    return this.httpClient.post<boolean>(this.DOMAIN + `/addReservation`, newReservation)
  }

}
