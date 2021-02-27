import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { MovieFilterData } from '../pipes/filter-movie.pipe';

@Injectable({
  providedIn: 'root'
})
export class FilterService {

  private filterObject = new Subject<any>();

  constructor() { }

  getActualFilters() : Observable<any> {
    return this.filterObject.asObservable();
  }

  updateFilters(movieFilters: MovieFilterData): void {
    this.filterObject.next(movieFilters);
  }


}
