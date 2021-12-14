import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { LoginDTO, Player } from './interfaces/auth';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  url = "http://localhost:8080/api/v1/login";

  constructor(private client: HttpClient ) { }

  login(cred: LoginDTO): Observable<Player> {

    const headerOptions = new HttpHeaders();
    headerOptions.set("Content-Type", "application/json")
    return this.client.post<Player>(this.url, cred,{headers: headerOptions});
  }
}
