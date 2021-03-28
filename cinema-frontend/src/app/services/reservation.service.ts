import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { NewReservation } from '../model/newReservation';
import { UserReservation } from '../model/userReservation';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  DOMAIN = environment.apiBase;

  constructor(
    private httpClient: HttpClient
  ) { }

  public getAllReservationsForUser(username: string): Observable<UserReservation[]> {
    return this.httpClient.post<UserReservation[]>(this.DOMAIN + `/reservations`, username)
  }

  public addNewReservation(newReservation: NewReservation): Observable<boolean> {
    return this.httpClient.post<boolean>(this.DOMAIN + `/addReservation`, newReservation)
  }

}