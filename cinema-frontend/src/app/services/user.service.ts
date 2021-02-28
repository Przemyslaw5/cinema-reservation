import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})

export class UserService {

  DOMAIN = environment.apiBase;

  constructor(
    private httpClient: HttpClient
  ) { }

  register(user: User) {
    return this.httpClient.post<boolean>(this.DOMAIN + '/register', user);
  }
  
  login(user: User) {
    return this.httpClient.post<boolean>(this.DOMAIN + '/login', user);
  }
}
