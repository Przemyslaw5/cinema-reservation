import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { LEADING_QUESTIONS } from 'src/app/model/question';
import { User } from 'src/app/model/user';
import { UserService } from 'src/app/services/user.service';

import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { LoginComponent } from '../login/login.component';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  user!: User;

  questions = LEADING_QUESTIONS

  registerInfo = '';
  badRegister: boolean = false;

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
  }

  public register(){
    
    this.user = {
      id: "papito",
      username: this.modelForm.value.username,
      leadingQuestion: this.modelForm.value.question,
      leadingAnswer: this.modelForm.value.answer
    }

    this.userService.register(this.user).subscribe(() => {
      console.log("Add new user successfully")
      this.badRegister = false;
      this.activeModal.dismiss('Cross click');
      this.userService.setUsername(this.user.username)
    }
    , error => {
      this.modelForm.reset();
      this.badRegister = true;
      console.log(error)
    });

  }

  public changeToLogin(){
    this.activeModal.dismiss('Cross click');
    const modalRef = this.modalService.open(LoginComponent);
  }

}
