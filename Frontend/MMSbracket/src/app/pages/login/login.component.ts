import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { CurrentUser, LoginDTO, Player } from 'src/app/interfaces/auth';

import { AuthService } from 'src/app/auth.service';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  @Output() login = new EventEmitter<boolean>();

  credentials: LoginDTO = new LoginDTO();


  constructor(private auth: AuthService) { }

  ngOnInit(): void {
  }

  logInToApp(){
    this.login.emit(true);
  }

  onSubmit(){
    this.auth.login(this.credentials).subscribe((data) => {

      

        localStorage.setItem("JWT", <string>data.headers.get("Authorization"))



        CurrentUser.username = data.body?.username;
        CurrentUser.email = data.body?.email;
        CurrentUser.firstName = data.body?.firstName;
        CurrentUser.lastName = data.body?.lastName;
        CurrentUser.playerId = data.body?.playerId;



        this.logInToApp();




    })
  }

}
