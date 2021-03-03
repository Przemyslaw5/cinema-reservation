export interface Seance {
    id: string;
    startDate: FilmDate;
    movieId: string;
    screeningRoomId: string;
    screeningRoomName: string;
}

export class FilmDate {
    year!: string;
    month!: string;
    day!: string;
    hour!: string;
    min!: string;
}