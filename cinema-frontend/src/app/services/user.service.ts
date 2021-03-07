import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})

export class UserService {

  DOMAIN = environment.apiBase;

  username?: string;

  constructor(
    private httpClient: HttpClient,
    private router: Router
  ) { }

  register(user: User) {
    return this.httpClient.post<boolean>(this.DOMAIN + '/register', user);
  }
  
  login(user: User) {
    return this.httpClient.post<boolean>(this.DOMAIN + '/login', user);
  }

  setUsername(username: string){
    this.username = username
    localStorage['username'] = username;
  }

  isLogged() {
    this.username = localStorage['username']
    return this.username != null
  }

  getUsername() {
    return this.username
  }

  logout() {
    this.username = undefined
    localStorage.removeItem('username')
    this.router.navigate(["/movies"])
  }
}
