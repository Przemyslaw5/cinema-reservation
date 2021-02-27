import { Component, OnInit } from '@angular/core';
import { CINEMA_PLACES_PLAN } from 'src/app/model/cinema';

@Component({
  selector: 'app-screening',
  templateUrl: './screening.component.html',
  styleUrls: ['./screening.component.scss']
})
export class ScreeningComponent implements OnInit {

  cinema_places_plan = CINEMA_PLACES_PLAN

  place = 0
  

  constructor(
    
  ) { }

  ngOnInit(): void {
  }

  getRow(row: string){
    return row.split("")
  }

  getPlace(){
    this.place += 1;
    return this.place;
  }

}
