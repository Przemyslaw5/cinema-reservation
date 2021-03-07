import { ReservedType } from "./reservedType";

export interface PlaceReservation {
    id: string;
    number: string;
    isReserved: ReservedType;
}