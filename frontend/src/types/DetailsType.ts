export type ButtonProps = {
    onClick: () => void;
}

export interface DetailsData {
    day:number;
    year:string;
    month:string;
    date:string;
    hour:number;
    spotId:number;
    spotName:string;
    dust:string;
    weather:string;
}