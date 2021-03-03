export interface Place {
    id: string;
    number: string;
    isReserved: ReservedType;
}

export enum ReservedType {
    FREE,
    RESERVED,
    RESERVED_BY_ME
}

export interface ScreeningRoom {
    id: string;
    name: string;
    placeNumber: number;
    placesPlan: string[];
}