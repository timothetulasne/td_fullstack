import { Component, inject, input, signal } from '@angular/core';
import { toObservable, toSignal } from '@angular/core/rxjs-interop';
import { catchError, of, switchMap } from 'rxjs';
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
  
  acteur = toSignal( // convertit en signal le resultat de ce qu'il y a dedans, donc l'observable
    toObservable(this.id).pipe( // id devient un Observable et quand id change, id emet une nvl valeur 
      switchMap(acteurId => { // detecte le chgmt de l'id et lance nvl requette http
        this.erreur.set(null);
        return this.acteurService.getActeurById(Number(acteurId)).pipe(
          catchError(() => {
            this.erreur.set('Impossible de charger les détails');
            return of(null);
          })
        );
      })
    )
  );
}
