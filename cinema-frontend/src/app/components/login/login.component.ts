import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { LEADING_QUESTIONS } from 'src/app/model/question';
import { User } from 'src/app/model/user';
import { UserService } from 'src/app/services/user.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
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
    console.log(this.modelForm.get('password')?.errors)
  }

  public login(){
    this.user = {
      id: "",
      username: this.modelForm.value.username,
      leadingQuestion: this.modelForm.value.question,
      leadingAnswer: this.modelForm.value.answer
    }

    this.userService.login(this.user).subscribe(() => {
      console.log("Successfully login")
      this.badLogin = false;
      this.activeModal.dismiss('Cross click');
      this.userService.setUsername(this.user.username)
    }
    , error => {
      this.modelForm.reset();
      this.badLogin = true;
      console.log(error)
    });

  }

  public changeToRegister(){
    this.activeModal.dismiss('Cross click');
    const modalRef = this.modalService.open(RegisterComponent);
  }
}
