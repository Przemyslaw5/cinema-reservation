import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { MovieDetailsComponent } from './components/movies/movie-details/movie-details.component';
import { MovieListComponent } from './components/movies/movie-list/movie-list.component';
import { RegisterComponent } from './components/register/register.component';
import { ScreeningComponent } from './components/screening/screening.component';

const routes: Routes = [
  { path: 'movies', component: MovieListComponent },
  { path: 'movies/details', component: MovieDetailsComponent },
  { path: 'movies/:id', component: MovieDetailsComponent },
  { path: 'login', component: LoginComponent },
  { path: 'sign-up', component: RegisterComponent },
  { path: 'screening', component: ScreeningComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
