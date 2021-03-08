import { ReservedType } from "./reservedType";

export interface Place {
    id: string;
    number: string;
    isReserved: ReservedType;
}
