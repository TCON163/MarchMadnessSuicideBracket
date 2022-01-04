import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { CurrentUser } from '../interfaces/auth';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnChanges {
  loggedIn: boolean = false;
  constructor() { }

  ngOnInit(): void {
    let id = Number.parseInt(<string>localStorage.getItem("playerId"));
    console.log(id)
    if (id !== NaN){
      this.loggedIn = true;
    }
  }

  ngOnChanges(changes: SimpleChanges): void {

  }

  setLoggedIn(logged:boolean){
    this.loggedIn = logged;
  }

}
