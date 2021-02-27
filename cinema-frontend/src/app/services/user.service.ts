import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})

export class UserService {

  DOMAIN = 'http://localhost:8080';

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
