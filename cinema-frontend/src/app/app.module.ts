import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { MovieListComponent } from './components/movies/movie-list/movie-list.component';
import { MovieComponent } from './components/movies/movie/movie.component';
import { HttpClientModule } from '@angular/common/http';
import { RatingComponent } from './components/rating/rating.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { MovieDetailsComponent } from './components/movies/movie-details/movie-details.component';
import { FilterMovieComponent } from './components/movies/filter-movie/filter-movie.component';
import { FilterMoviePipe } from './pipes/filter-movie.pipe';
import { FormsModule } from '@angular/forms';
import { NgxSliderModule } from '@angular-slider/ngx-slider';
import { ReactiveFormsModule, FormControl, Validators } from '@angular/forms';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { ReservationComponent } from './components/reservation/reservation.component';
import { UserReservationsComponent } from './components/user-reservations/user-reservations.component';
import { AddOrEditMovieComponent } from './components/movies/add-or-edit-movie/add-or-edit-movie.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { TimepickerModule } from 'ngx-bootstrap/timepicker';
import { AddOrEditSeancesComponent } from './components/movies/add-or-edit-seances/add-or-edit-seances.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    MovieListComponent,
    MovieComponent,
    RatingComponent,
    MovieDetailsComponent,
    FilterMovieComponent,
    FilterMoviePipe,
    LoginComponent,
    RegisterComponent,
    ReservationComponent,
    UserReservationsComponent,
    AddOrEditMovieComponent,
    AddOrEditSeancesComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    NgxSliderModule,
    BrowserAnimationsModule,
    BsDatepickerModule.forRoot(),
    TimepickerModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
