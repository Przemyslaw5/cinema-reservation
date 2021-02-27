import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { LEADING_QUESTIONS } from 'src/app/model/question';
import { User } from 'src/app/model/user';
import { UserService } from 'src/app/services/user.service';

import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { RegisterComponent } from '../register/register.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  @Input() rate!: number;

  user!: User;

  questions = LEADING_QUESTIONS

  credentials = {
    email: '',
    password: ''
  }

  registerInfo = '';
  badLogin: boolean = false;


  modelForm = new FormGroup({
    username: new FormControl('', [Validators.required, Validators.minLength(5)]),
    question: new FormControl('', [Validators.required]),
    answer: new FormControl('', [Validators.required])
  })




  constructor(
    public activeModal: NgbActiveModal,
    private userService: UserService,
    private modalService: NgbModal
  ) { }


  ngOnInit(): void {
    // this.register()
    console.log(this.modelForm.get('password')?.errors)
  }

  login(){
    
    this.user = {
      username: this.modelForm.value.username,
      leadingQuestion: this.modelForm.value.question,
      leadingAnswer: this.modelForm.value.answer
    }

    this.userService.login(this.user).subscribe(() => {
      console.log("Successfully login")
      this.badLogin = false;
      this.activeModal.dismiss('Cross click');
    }
    , error => {
      this.modelForm.reset();
      this.badLogin = true;
      console.log(error)
    });

  }


  changeToRegister(){
    this.activeModal.dismiss('Cross click');
    const modalRef = this.modalService.open(RegisterComponent);
    // modalRef.componentInstance.rate = rate;
  }



}
