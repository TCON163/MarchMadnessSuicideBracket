import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from 'rxjs';
import { DateDTO, Game, Tourney, TPlayer } from './interfaces/tourney';

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

  getListOfGamesByDate(date: DateDTO): Observable<Game[]>{
    const headerOptions = new HttpHeaders();
    headerOptions.set("Content-Type", "application/json");
    return this.client.post<Game[]>(this.url +"/games/date",date,{headers: headerOptions})
  }

  createTourney(tourney: Tourney, id: number): Observable<Tourney>{
    const headerOptions = new HttpHeaders();
    headerOptions.set("Content-Type", "application/json");
    return this.client.post<Tourney>(this.url + "/tourney/"+ id, tourney, {headers: headerOptions})
  }

  addTPtoTourney(playerId: number, tourneyId: number): Observable<TPlayer>{
    const headerOptions = new HttpHeaders();
    headerOptions.set("Content-Type", "application/json");
    let body = new TPlayer();
    body.alive = true;
    return this.client.post<TPlayer>(this.url +"/tp/" + playerId +"/tourney/"+tourneyId,body,{headers: headerOptions});
  }


}
