import { PlatformLocation } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { UserReservation } from 'src/app/model/userReservation';
import { CinemaService } from 'src/app/services/cinema.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-reservations',
  templateUrl: './user-reservations.component.html',
  styleUrls: ['./user-reservations.component.scss']
})
export class UserReservationsComponent implements OnInit {

  username?: string;
  reservations?: UserReservation[];

  constructor(
    private cinemaService: CinemaService,
    private userService: UserService
  ) { }

  ngOnInit(): void {
    this.getReservations()
  }

  getReservations() {
    this.cinemaService.getAllReservationsForUser(this.userService.getUsername()!).subscribe(reservations => {
      this.reservations = reservations;
    }
    , error => {
      console.log(error)
    });
  }

}
