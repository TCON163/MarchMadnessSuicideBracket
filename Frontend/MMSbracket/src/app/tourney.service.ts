import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from 'rxjs';
import { DateDTO, Game, Picks, Tourney, TPlayer } from './interfaces/tourney';
import { CurrentUser, Player } from './interfaces/auth';

@Injectable({
  providedIn: 'root'
})
export class TourneyService {

  url = "http://localhost:5000/api/v1";

  private httpOptions ={
    headers: new HttpHeaders().set("Authorization",<string> localStorage.getItem("JWT"))
  }

  constructor(private client: HttpClient ) {




   }


  getListOfTourneyByPlayerId(id: number): Observable<Tourney[]> {
    console.log(this.httpOptions)

    return this.client.get<Tourney[]>(this.url + "/players/tourney/" + id, this.httpOptions)
  }


  getSetOfTPlayerByPlayerId(id: number): Observable<TPlayer[]> {

    console.log(this.httpOptions)
    return this.client.get<TPlayer[]>(this.url + "/players/tps/" + id, this.httpOptions)
  }


  getTourneyByTouneyId(id: number): Observable<Tourney> {
    const headerOptions = new HttpHeaders().set("Authorization", <string> localStorage.getItem("JWT")).set("Content-Type", "application/json")
    console.log(headerOptions)
    return this.client.get<Tourney>(this.url + "/tourney/" + id, {headers: headerOptions});
  }

  getListOfGamesByDate(date: DateDTO): Observable<Game[]>{
    const headerOptions = new HttpHeaders();
    headerOptions.set("Authorization", <string> localStorage.getItem("JWT"))
    headerOptions.set("Content-Type", "application/json")
    console.log(headerOptions)
    return this.client.post<Game[]>(this.url +"/games/date",date,{headers: headerOptions})
  }

  createTourney(tourney: Tourney, id: number): Observable<Tourney>{
    const headerOptions = new HttpHeaders();
    headerOptions.set("Authorization", <string> localStorage.getItem("JWT"))
    headerOptions.set("Content-Type", "application/json")
    console.log(headerOptions)
    return this.client.post<Tourney>(this.url + "/tourney/"+ id, tourney, {headers: headerOptions})
  }

  addTPtoTourney(playerId: number, tourneyId: number): Observable<TPlayer>{
    const headerOptions = new HttpHeaders();
    headerOptions.set("Authorization", <string> localStorage.getItem("JWT"))
    headerOptions.set("Content-Type", "application/json")
    console.log(headerOptions)
    let body = new TPlayer();
    body.alive = true;
    return this.client.post<TPlayer>(this.url +"/tp/" + playerId +"/tourney/"+tourneyId,body,{headers: headerOptions});
  }

  getUserByUsername(username: string): Observable<Player> {
    const headerOptions = new HttpHeaders();
    headerOptions.set("Authorization", <string> localStorage.getItem("JWT"))
    headerOptions.set("Content-Type", "application/json")
    console.log(headerOptions)
    return this.client.get<Player>(this.url+"/players/username/"+ username,{headers: headerOptions});
  }

  makePicks(tPlayerId: number, teamId: number, gameId:number): Observable<Picks> {
    const headerOptions = new HttpHeaders();
    headerOptions.set("Authorization", <string> localStorage.getItem("JWT"))
    headerOptions.set("Content-Type", "application/json")
    console.log(headerOptions)
    return this.client.get<Picks>(this.url+"picks/" + tPlayerId + "/team/" + teamId + "/game/" + gameId, {headers: headerOptions});
  }


}
