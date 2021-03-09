import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddOrEditSeancesComponent } from './add-or-edit-seances.component';

describe('AddOrEditSeancesComponent', () => {
  let component: AddOrEditSeancesComponent;
  let fixture: ComponentFixture<AddOrEditSeancesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddOrEditSeancesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddOrEditSeancesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
