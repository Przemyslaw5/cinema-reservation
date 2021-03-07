import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Movie } from '../model/movie';
import { Place } from '../model/place';
import { Reservation } from '../model/reservation';
import { ScreeningRoom } from '../model/screeningRoom';
import { Seance } from '../model/seance';
import { User } from '../model/user';

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

  public getPlacesFromSeanceId(seanceId: string): Observable<Place[]> {
    return this.httpClient.get<Place[]>(this.DOMAIN + `/screening-rooms/${seanceId}/places`)
  }

  public getAllGenres(): Observable<string[]> {
    return this.httpClient.get<string[]>(this.DOMAIN + `/genres`)
  }

  public getAllReservationsForUser(user: User): Observable<Reservation[]> {
    return this.httpClient.get<Reservation[]>(this.DOMAIN + `/reservations`)
  }

}
