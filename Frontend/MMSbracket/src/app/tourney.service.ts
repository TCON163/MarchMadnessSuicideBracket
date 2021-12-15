import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from 'rxjs';
import { Tourney, TPlayer } from './interfaces/tourney';

@Injectable({
  providedIn: 'root'
})
export class TourneyService {

  url = "http://localhost:5000/api/v1";

  constructor(private client: HttpClient ) { }

  getListOfTourneyByPlayerId(id: number): Observable<Tourney[]> {
    const headerOptions = new HttpHeaders();
    headerOptions.set("Content-Type", "application/json");
    return this.client.get<Tourney[]>(this.url + "/players/tourney/" + id, {headers: headerOptions})
  }


  getSetOfTPlayerByPlayerId(id: number): Observable<TPlayer[]> {
    const headerOptions = new HttpHeaders();
    headerOptions.set("Content-Type", "application/json");
    return this.client.get<TPlayer[]>(this.url + "/players/tps/" + id, {headers: headerOptions})
  }


  getTourneyByTouneyId(id: number): Observable<Tourney> {
    const headerOptions = new HttpHeaders();
    headerOptions.set("Content-Type", "application/json");
    return this.client.get<Tourney>(this.url + "/tourney/" + id, {headers: headerOptions});
  }


}
