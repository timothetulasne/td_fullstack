import { inject, Injectable, Service } from '@angular/core';
import { Acteur } from './acteur.model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: "root" })
export class ActeurService {
  
  private http = inject(HttpClient);
  private url = "/api/acteurs";
  
  getAll(): Observable<Acteur[]> {
    return this.http.get<Acteur[]>(this.url);
  }
}