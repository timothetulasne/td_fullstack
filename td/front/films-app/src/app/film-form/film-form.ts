import { Component, inject, input, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Film } from '../film.model';
import { FilmService } from '../film-service';
import { Router, RouterLink } from '@angular/router';
import { toObservable, toSignal } from '@angular/core/rxjs-interop';
import { catchError, of, switchMap } from 'rxjs';

@Component({
  selector: 'app-film-form',
  standalone: true,
  imports: [FormsModule, RouterLink],
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

  id = input<string>();

  erreur = signal<string | null>(null);

  currentUrl = this.router.url;

  film$ = toSignal( // convertit en signal le resultat de ce qu'il y a dedans, donc l'observable
    toObservable(this.id).pipe( // id devient un Observable et quand id change, id emet une nvl valeur 
      switchMap(filmId => { // detecte le chgmt de l'id et lance nvl requette http
        this.erreur.set(null);
        return this.filmService.getFilmById(Number(filmId)).pipe(
          catchError(() => {
            this.erreur.set('Impossible de charger les détails');
            return of(null);
          })
        );
      })
    )
  );


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