export interface Movie {
    id: string;
    title: string;
    description: string;
    image: string;
    rate: number;
    ratesNumber: number;
    genre: MovieGenre;
    durationTime: number;
}

export enum MovieGenre {
    "Empty",
    "All",
    "Action",
    "Comedy",
    "Drama",
    "Fantasy",
    "Horror",
    "Science fiction",
}

export interface ReservationDate {
    year: string;
    month: string;
    day: string;
    hour: string;
  }