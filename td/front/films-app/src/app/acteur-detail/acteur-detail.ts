import { Component, inject, input, signal } from '@angular/core';
import { toObservable, toSignal } from '@angular/core/rxjs-interop';
import { catchError, forkJoin, of, switchMap } from 'rxjs';
import { ActeurService } from '../acteur-service';
import { RouterLink } from '@angular/router';

@Component({
  imports: [RouterLink],
  selector: 'app-acteur-detail',
  styleUrl: './acteur-detail.css',
  templateUrl: './acteur-detail.html',
})
export class ActeurDetail {
  
  private acteurService = inject(ActeurService);

  id = input.required<string>();

  erreur = signal<string | null>(null);
  
  donnees = toSignal(
    toObservable(this.id).pipe(
      switchMap(acteurId => {
        this.erreur.set(null);
        const numericId = Number(acteurId);

        return forkJoin({ // permet d'avoir deux informations dans un signal
          acteur: this.acteurService.getActeurById(numericId),
          films: this.acteurService.getFilmByActeurId(numericId)
        }).pipe(
          catchError(() => {
            this.erreur.set('Impossible de charger les détails');
            return of(null);
          })
        );
      })
    )
  );
}
