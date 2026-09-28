import { Component, signal } from '@angular/core';
import { FilmCard } from './film-card/film-card';
import { FilmList } from './film-list/film-list';

@Component({
  imports: [FilmList],
  selector: 'app-root',
  styleUrl: './app.css',
  templateUrl: './app.html',
})
export class App {
  protected readonly title = signal('film-app');
}
