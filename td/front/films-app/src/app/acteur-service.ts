import { inject, Injectable, Service } from '@angular/core';
import { Acteur } from './acteur.model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Film } from './film.model';

@Injectable({ providedIn: "root" })
export class ActeurService {
  
  private http = inject(HttpClient);
  private url = "/api/acteurs";
  
  getAll(): Observable<Acteur[]> {
    return this.http.get<Acteur[]>(this.url);
  }

  getActeurById(id: number): Observable<Acteur> {
      return this.http.get<Acteur>(`${this.url}/${id}`);
  }

  getFilmByActeurId(id: number): Observable<Film[]> {
      return this.http.get<Film[]>(`${this.url}/${id}/films`);
  }
}