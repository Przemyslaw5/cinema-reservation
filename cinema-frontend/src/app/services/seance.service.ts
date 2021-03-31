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
export class SeanceService {

  DOMAIN = environment.apiBase;

  constructor(
    private httpClient: HttpClient
  ) { }

  public getSeancesFromMovieId(id: string): Observable<Seance[]> {
    return this.httpClient.get<Seance[]>(this.DOMAIN + `/movies/${id}/seances`);
  }

  public getPlacesFromSeanceId(seanceId: string): Observable<Place[]> {
    return this.httpClient.get<Place[]>(this.DOMAIN + `/screening-rooms/${seanceId}/places`)
  }

  public addNewSeance(newSeance: NewSeance): Observable<Seance> {
    return this.httpClient.post<Seance>(this.DOMAIN + `/seance/add`, newSeance)
  }

  public deleteSeance(id: string) {
    return this.httpClient.delete(this.DOMAIN + `/remove/seance/${id}`)
  }

}
