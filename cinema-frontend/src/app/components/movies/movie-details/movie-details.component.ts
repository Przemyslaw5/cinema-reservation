import { Component, OnInit } from '@angular/core';
import { Movie } from 'src/app/model/movie';
import { ActivatedRoute } from '@angular/router';
import { CinemaService } from 'src/app/services/cinema.service';


@Component({
  selector: 'app-movie-details',
  templateUrl: './movie-details.component.html',
  styleUrls: ['./movie-details.component.scss']
})
export class MovieDetailsComponent implements OnInit {

  movie!: Movie;

  constructor(
    private cinemaService: CinemaService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.getCourse();
  }

  getCourse(){
    const id = this.route.snapshot.paramMap.get("id");
    if (id != null){
      this.cinemaService.getMovieById(id).subscribe(movie =>  {
        this.movie = movie;
      });
    }
  }

}
