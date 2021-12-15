import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/auth.service';
import { RegisterPlayer } from 'src/app/interfaces/auth';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  newUser = new RegisterPlayer();

  id!:number;

  constructor(private auth: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit(){
    this.auth.register(this.newUser).subscribe(data => {
      this.id = data.playerId;
      localStorage.setItem("playerId", data.playerId.toString())
      localStorage.setItem("username", data.username)
      localStorage.setItem("firstName", data.firstName)
      localStorage.setItem("lastName", data.lastName)
      localStorage.setItem("email", data.email)

      

    },error =>{
      alert("Username is already in use!")
      console.log(error);
    });

    setTimeout(()=>{
      this.auth.admin(this.id).subscribe(data => {
        if(this.id === data.adminId){
          this.router.navigate([""]);
        }
      })
    }, 500)

    
  }

}
