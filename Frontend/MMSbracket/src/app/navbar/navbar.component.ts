import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { CurrentUser } from '../interfaces/auth';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit, OnChanges {

  @Input() loggedIn!: boolean;
  @Output() logout = new EventEmitter<boolean>();

  username = <string>localStorage.getItem("username");




  constructor() { }

  ngOnInit(): void {
  }

  ngOnChanges(changes: SimpleChanges): void {
      this.username = <string>localStorage.getItem("username");
  }



  appLogOut(){
    this.logout.emit(false);
    CurrentUser.clear();
    localStorage.clear();
    sessionStorage.clear();
  }

  ifTcon():boolean{
    if(this.username ==="TCON"){
      return true;
    }
    return false;
  }

}
