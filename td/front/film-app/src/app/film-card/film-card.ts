import { Component, signal } from '@angular/core';
import { Film } from '../film.model';
import { FormsModule } from '@angular/forms';

@Component({
  imports: [FormsModule],
  selector: 'app-film-card',
  styleUrl: './film-card.css',
  templateUrl: './film-card.html',
})
export class FilmCard {
  film = signal<Film>({
    id: 2,
    titre: "Titanic",
    realisateur: "Spielberg",
    dateSortie: "01/01/2000",
    genre: "Drame"
  })

  titre = signal<string>("");

  effacerTitre() {
    this.titre.set("");
  }
}
