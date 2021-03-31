import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddOrEditMovieComponent } from './add-or-edit-movie.component';

describe('AddOrEditMovieComponent', () => {
  let component: AddOrEditMovieComponent;
  let fixture: ComponentFixture<AddOrEditMovieComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddOrEditMovieComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddOrEditMovieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
