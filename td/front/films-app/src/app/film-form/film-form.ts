import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Film } from '../film.model';

@Component({
  selector: 'app-film-form',
  standalone: true,
  imports: [FormsModule],
  styleUrl: './film-form.css',
  templateUrl: './film-form.html',
})
export class FilmForm {
  film: Partial<Film> = {
    titre: '',
    realisateur: '',
    dateSortie: '',
    genre: ''
  };

  enregistrer() {
    console.log(this.film);
  }
}