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
    Empty,
    Komedia,
    Thriller,
    Przygodowy,
    Kryminał
}