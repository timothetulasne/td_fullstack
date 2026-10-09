import { Component, inject, input, OnInit, signal } from '@angular/core';
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
export class FilmForm implements OnInit{
  private filmService = inject(FilmService);
  private router = inject(Router);

  film = signal<Partial<Film>>({
    titre: '',
    realisateur: '',
    dateSortie: '',
    genre: ''
  });

  id = input<string>();

  erreur = signal<string | null>(null);

  currentUrl = this.router.url;

  ngOnInit(): void {
    const filmId = this.id();
    if (filmId) {
      this.filmService.getFilmById(Number(filmId)).subscribe({
        next: (value) => {
          this.film.set(value);
        },
      });
    }
    
  }

  sauvegarder(): void {
    if (this.id()) {
      this.modifier();
    } else {
      this.creer();
    }
  }

  modifier() {
    const filmId = Number(this.id());
    if (!filmId) return;

    this.filmService.updateFilmById(filmId, this.film()).subscribe({
      next: () => {
        this.router.navigate(['/films']);
      }
    });
  }

  creer() {
    this.filmService.createFilm(this.film()).subscribe({
      next: (filmCree) => {
        this.router.navigate(['/films']);
      }
    });
  }

  
}