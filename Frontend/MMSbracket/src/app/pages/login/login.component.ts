import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { CurrentUser, LoginDTO } from 'src/app/interfaces/auth';

import { AuthService } from 'src/app/auth.service';

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
    this.auth.login(this.credentials).subscribe(data => {
      localStorage.setItem("playerId", data.playerId.toString())
      localStorage.setItem("username", data.username)
      localStorage.setItem("firstName", data.firstName)
      localStorage.setItem("lastName", data.lastName)
      localStorage.setItem("email", data.email)

      this.logInToApp();
    

    })
  }

}
