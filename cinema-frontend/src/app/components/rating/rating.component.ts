import { Component, Input, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { LoginComponent } from '../login/login.component';
import { RegisterComponent } from '../register/register.component';

@Component({
  selector: 'app-rating',
  templateUrl: './rating.component.html',
  styleUrls: ['./rating.component.scss']
})
export class RatingComponent implements OnInit {

  @Input() movieRate!: number;
  @Input() readOnly!: boolean;

  // currentRate: number;
  hovered = "";
  userAlreadyRate: boolean = false;
  userNumberRate: number;

  constructor(
    private modalService: NgbModal
  ) { 
    this.userNumberRate = 3;
  }

  ngOnInit(): void {
  }

  //This method change the namber of stars after click
  onRateChange(rate: number){
    const modalRef = this.modalService.open(LoginComponent);
    modalRef.componentInstance.rate = rate;
  }

}
