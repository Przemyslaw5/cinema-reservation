import { Place } from "./place";
import { ReservedType } from "./reservedType";

export interface Reservation {
    id: string;
    places: Array<Place>;
    isReserved: ReservedType;
}