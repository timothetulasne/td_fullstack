import { Component, signal } from '@angular/core';
import { Film } from '../film.model';

@Component({
  imports: [],
  selector: 'app-film-list',
  styleUrl: './film-list.css',
  templateUrl: './film-list.html',
})
export class FilmList {
  films = signal<Film[]>([{
    id: 2,
    titre: "Titanic",
    realisateur: "Spielberg",
    dateSortie: "01/01/2000",
    genre: "Drame"
  }, {
    id: 4,
    titre: "Transformers",
    realisateur: "Spielberg",
    dateSortie: "02/01/2000",
    genre: "Action"
  }, {
    id: 15,
    titre: "Up",
    realisateur: "Disney",
    dateSortie: "01/01/2004",
    genre: "Comédie"
  }, {
    id: 1,
    titre: "Cars",
    realisateur: "Disney",
    dateSortie: "01/01/2003",
    genre: "Comédie"
  }])
}
