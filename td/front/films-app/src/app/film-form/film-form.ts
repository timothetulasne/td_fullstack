import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Film } from '../film.model';
import { FilmService } from '../film-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-film-form',
  standalone: true,
  imports: [FormsModule],
  styleUrl: './film-form.css',
  templateUrl: './film-form.html',
})
export class FilmForm {
  private filmService = inject(FilmService);
  private router = inject(Router);

  film: Partial<Film> = {
    titre: '',
    realisateur: '',
    dateSortie: '',
    genre: ''
  };

  enregistrer() {
    this.filmService.createFilm(this.film).subscribe({
      next: (filmCree) => {
        console.log('Film bien créé :', filmCree);
        this.router.navigate(['/films']);
      },
      error: (err) => {
        console.error('Erreur création du film :', err);
      }
    });
  }
}