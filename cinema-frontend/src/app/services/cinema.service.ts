import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Movie } from '../model/movie';
import { Place, ScreeningRoom } from '../model/reservation';
import { Seance } from '../model/seance';

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

  getSeancesFromMovieId(id: string) {
    return this.httpClient.get<Seance[]>(this.DOMAIN + `/movies/${id}/seances`);
  }

  getScreeningRoom(id: string) {
    return this.httpClient.get<ScreeningRoom>(this.DOMAIN + `/screening-rooms/${id}`)
  }

  getPlacesFromSeanceId(seanceId: string) {
    return this.httpClient.get<Place[]>(this.DOMAIN + `/screening-rooms/${seanceId}/places`)
  }

}
