import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Admin, LoginDTO, Player, RegisterPlayer } from './interfaces/auth';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  url = "http://localhost:5000/api/v1";

  constructor(private client: HttpClient ) { }

  login(cred: LoginDTO): Observable<Player> {

    const headerOptions = new HttpHeaders();
    headerOptions.set("Content-Type", "application/json")
    return this.client.post<Player>(this.url +"/login", cred,{headers: headerOptions});
  }


  register(newUser: RegisterPlayer): Observable<Player>{

    const headerOptions = new HttpHeaders();
    
    headerOptions.set("Content-Type", "application/json");
    return this.client.post<Player>(this.url + "/players", newUser, {headers: headerOptions});
  }


  admin(id: number): Observable<Admin> {
    const headerOptions = new HttpHeaders();
    headerOptions.set("Content-Type", "application/json")
    return this.client.post<Admin>(this.url + "/admin/" + id, null, {headers: headerOptions})
  }
}
