import { Component, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-or-edit-seances',
  templateUrl: './add-or-edit-seances.component.html',
  styleUrls: ['./add-or-edit-seances.component.scss']
})
export class AddOrEditSeancesComponent implements OnInit {

  modelForm = new FormGroup({
    title: new FormControl('asd', [Validators.required, Validators.minLength(5)]),
    seances: new FormArray([
      // new FormControl('first', [Validators.required]), new FormControl('second', [Validators.required]),
      new FormGroup({
        time: new FormControl(new Date()),
        screeningRoom: new FormControl('', [Validators.required])
      })
    ]),
    // question: new FormControl('', [Validators.required]),
    // answer: new FormControl('', [Validators.required])
  })

  get seances(): FormArray {
    return this.modelForm.get('seances') as FormArray;
  }

  // time: Date = new Date();
  // screeningRoom: string = ""
  // date: Date = new Date()

  allOptions = ["ES", "SAFA", "DASD"]

  show() {
    // console.log(this.time)
  }

  addAnotherSeance() {
    (<FormArray>this.modelForm.get('seances')).push(new FormGroup({
      time: new FormControl(new Date()),
      screeningRoom: new FormControl('', [Validators.required])
    }))
  }

  constructor() { }

  ngOnInit(): void {
    // (<FormArray>this.modelForm.get('seances')).push(new FormControl('EASE'));
    // (<FormArray>this.modelForm.get('seances')).push(new FormControl('dasda'))
    // this.modelForm.value.seances.push(new FormControl('EASE'), [Validators.required])
    // this.modelForm.value.seances.push(new FormControl('dasd'))
    // this.modelForm.value.seances.push(new FormControl('hhsf'))
    // this.modelForm.value.seances.push(new FormControl('PESAS'), [Validators.required])
    // this.modelForm.value.title.value = "EASE"
    // console.log(this.modelForm.value.seances)
    // console.log((<FormArray>this.modelForm.get('seances')).at(2).value)
    // console.log(this.modelForm.get(['seances', 0])?.value)
    // console.log(this.seances.value)
  }

}
