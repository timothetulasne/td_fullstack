import { Component, signal } from '@angular/core';
import { FilmCard } from './film-card/film-card';

@Component({
  imports: [FilmCard],
  selector: 'app-root',
  styleUrl: './app.css',
  templateUrl: './app.html',
})
export class App {
  protected readonly title = signal('film-app');
}
