import { Film } from "./film.model";

export interface Acteur {
    id: number;
    nom: string;
    prenom: string;
    films: Film[];
}