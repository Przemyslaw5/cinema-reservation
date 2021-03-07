export interface UserReservation {
    id: string;
    title: string;
    screeningRoomName: string;
    date: string,
    places: Array<number>,
    secretWord: string
}