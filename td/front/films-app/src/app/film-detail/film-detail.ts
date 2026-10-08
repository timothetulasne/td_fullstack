import { Component, inject, input, signal } from '@angular/core';
import { toSignal, toObservable } from '@angular/core/rxjs-interop';
import { RouterLink } from '@angular/router';
import { catchError, of, switchMap } from 'rxjs';
import { FilmService } from '../film-service';
import { DatePipe, UpperCasePipe } from '@angular/common';

@Component({
  selector: 'app-film-detail',
  imports: [RouterLink, DatePipe, UpperCasePipe],
  templateUrl: './film-detail.html',
  styleUrl: './film-detail.css'
})
export class FilmDetail {
  private filmService = inject(FilmService);

  id = input.required<string>();

  erreur = signal<string | null>(null);

  film = toSignal( // convertit en signal le resultat de ce qu'il y a dedans, donc l'observable
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
}