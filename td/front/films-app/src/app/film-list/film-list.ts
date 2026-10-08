import { Component, inject, signal } from '@angular/core';
import { AsyncPipe } from '@angular/common';
import { RouterLink } from '@angular/router';
import { catchError, of } from 'rxjs';
import { FilmService } from '../film-service';

@Component({
  selector: 'app-film-list',
  imports: [AsyncPipe, RouterLink],
  templateUrl: './film-list.html',
  styleUrl: './film-list.css'
})
export class FilmList {
  private filmService = inject(FilmService);

  erreur = signal<string | null>(null);

  films$ = this.filmService.getAll().pipe(
    catchError(() => {
      this.erreur.set('Serveur pas joignable');
      return of([]);
    })
  );
}