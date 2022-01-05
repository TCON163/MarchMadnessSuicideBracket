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

    return this.client.get<Tourney>(this.url + "/tourney/"+id, this.httpOptions);
  }

  getListOfGamesByDate(date: DateDTO): Observable<Game[]>{

    return this.client.post<Game[]>(this.url +"/games/date",date,this.httpOptions)
  }

  createTourney(tourney: Tourney): Observable<Tourney>{

    return this.client.post<Tourney>(this.url + "/tourney", tourney,this.httpOptions)
  }

  addTPtoTourney( tourneyId: number, playerId: number): Observable<TPlayer>{

    let body = new TPlayer();
    body.alive = true;
    return this.client.post<TPlayer>(this.url +"/tp/"+playerId +"/tourney/"+tourneyId,body,this.httpOptions);
  }

  getUserByUsername(username: string): Observable<Player> {

    return this.client.get<Player>(this.url+"/players/username/"+ username,this.httpOptions);
  }

  makePicks(tPlayerId: number, teamId: number, gameId:number): Observable<Picks> {

    return this.client.get<Picks>(this.url+"picks/" + tPlayerId + "/team/" + teamId + "/game/" + gameId, this.httpOptions);
  }


}
