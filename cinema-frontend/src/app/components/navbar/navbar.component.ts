import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { UserService } from 'src/app/services/user.service';
import { LoginComponent } from '../login/login.component';
import { RegisterComponent } from '../register/register.component';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  constructor(
    private userService: UserService,
    private modalService: NgbModal
  ) { }

  ngOnInit(): void {
  }

  public isLogged() {
    return this.userService.isLogged()
  }

  public login() {
    const modalRef = this.modalService.open(LoginComponent);
  }

  public register() {
    const modalRef = this.modalService.open(RegisterComponent);
  }

  public getUsername() {
    return this.userService.getUsername()
  }

  public logout() {
    this.userService.logout()
  }

}
