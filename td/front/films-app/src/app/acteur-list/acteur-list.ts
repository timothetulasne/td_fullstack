import { AsyncPipe } from '@angular/common';
import { Component, inject, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { catchError, of } from 'rxjs';
import { ActeurService } from '../acteur-service';

@Component({
  imports: [AsyncPipe, RouterLink],
  selector: 'app-acteur-list',
  styleUrl: './acteur-list.css',
  templateUrl: './acteur-list.html',
})
export class ActeurList {
  private acteurService = inject(ActeurService);

  erreur = signal<string | null>(null);

  acteurs$ = this.acteurService.getAll().pipe(
    catchError(() => {
      this.erreur.set('Serveur pas joignable');
      return of([]);
    })
  );
}
