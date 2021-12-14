import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnChanges {
  loggedIn: boolean = false;
  constructor() { }

  ngOnInit(): void {
    let id = localStorage.getItem("playerId");
    if (id !== null){
      this.loggedIn = true;
    }
  }

  ngOnChanges(changes: SimpleChanges): void {
      
  }

  setLoggedIn(logged:boolean){
    this.loggedIn = logged;
  }

}
