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

  

  constructor(private auth: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit(){
    this.auth.register(this.newUser).subscribe(data => {



      this.router.navigate([""]);

    },error =>{
      alert("Username is already in use!")
      console.log(error);
    });





  }

}
