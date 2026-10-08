import { Component, inject, signal } from '@angular/core';
import { AsyncPipe, DatePipe } from '@angular/common';
import { RouterLink } from '@angular/router';
import { catchError, of } from 'rxjs';
import { FilmService } from '../film-service';
import { Film } from '../film.model';

@Component({
  selector: 'app-film-list',
  imports: [AsyncPipe, RouterLink, DatePipe],
  templateUrl: './film-list.html',
  styleUrl: './film-list.css'
})
export class FilmList {
  private filmService = inject(FilmService);

  erreur = signal<string | null>(null);

  estAncien(f: Film) {
    return new Date(f.dateSortie).getFullYear() < 2000;
  }

  films$ = this.filmService.getAll().pipe(
    catchError(() => {
      this.erreur.set('Serveur pas joignable');
      return of([]);
    })
  );
}