import { PlatformLocation } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { CinemaService } from 'src/app/services/cinema.service';

@Component({
  selector: 'app-user-reservations',
  templateUrl: './user-reservations.component.html',
  styleUrls: ['./user-reservations.component.scss']
})
export class UserReservationsComponent implements OnInit {

  user!: User;

  constructor(
    private cinemaService: CinemaService
  ) { }

  ngOnInit(): void {
    this.getData()
  }

  getData() {
    this.user = { 
      id: "es",
      username: "ease",
      leadingQuestion: "ease",
      leadingAnswer: "DAsda"
    }
    this.cinemaService.getAllReservationsForUser(this.user).subscribe(() => {
      console.log("Get reservations from user successfully")
    }
    , error => {
      console.log(error)
    });
  }

}
