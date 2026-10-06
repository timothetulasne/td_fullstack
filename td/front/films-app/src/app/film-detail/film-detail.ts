import { Component, inject, input, OnInit, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { FilmService } from '../film-service';
import { Film } from '../film.model';

@Component({
  imports: [RouterLink],
  selector: 'app-film-detail',
  styleUrl: './film-detail.css',
  templateUrl: './film-detail.html',
})
export class FilmDetail implements OnInit {
  private readonly filmService = inject(FilmService);

  readonly id = input.required<string>();
  readonly film = signal<Film | null>(null);
  readonly erreur = signal<string | null>(null);

  ngOnInit() {
    this.recharger();
  }

  recharger() {
    const filmId = Number(this.id());
    this.filmService.getFilmById(filmId).subscribe({
      next: (f) => this.film.set(f),
      error: () => this.erreur.set('Impossible de charger le film.')
    });
  }
}