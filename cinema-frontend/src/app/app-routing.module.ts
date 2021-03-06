import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { AddOrEditMovieComponent } from './components/movies/add-or-edit-movie/add-or-edit-movie.component';
import { AddOrEditSeancesComponent } from './components/movies/add-or-edit-seances/add-or-edit-seances.component';
import { MovieDetailsComponent } from './components/movies/movie-details/movie-details.component';
import { MovieListComponent } from './components/movies/movie-list/movie-list.component';
import { RegisterComponent } from './components/register/register.component';
import { ReservationComponent } from './components/reservation/reservation.component';
import { UserReservationsComponent } from './components/user-reservations/user-reservations.component';

const routes: Routes = [
  { path: '', component: MovieListComponent },
  { path: 'movies', component: MovieListComponent },
  { path: 'movies/:id', component: MovieDetailsComponent },
  { path: 'login', component: LoginComponent },
  { path: 'sign-up', component: RegisterComponent },
  { path: 'movies/:id/reservations', component: ReservationComponent },
  { path: 'reservations', component: UserReservationsComponent },
  { path: 'add', component: AddOrEditMovieComponent },
  { path: 'movies/:id/seances', component: AddOrEditSeancesComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
