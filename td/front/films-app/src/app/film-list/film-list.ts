import { Component, inject } from '@angular/core';
import { toSignal } from '@angular/core/rxjs-interop';
import { RouterLink } from '@angular/router';
import { FilmService } from '../film-service';

@Component({
  imports: [RouterLink],
  selector: 'app-film-list',
  styleUrl: './film-list.css',
  templateUrl: './film-list.html',
})
export class FilmList {
  private readonly filmService = inject(FilmService);

  readonly films = toSignal(this.filmService.getAll(), { initialValue: [] });
}