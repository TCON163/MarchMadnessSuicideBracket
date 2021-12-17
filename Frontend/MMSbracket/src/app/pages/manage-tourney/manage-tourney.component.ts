import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Player } from 'src/app/interfaces/auth';
import { Tourney } from 'src/app/interfaces/tourney';
import { TourneyService } from 'src/app/tourney.service';

@Component({
  selector: 'app-manage-tourney',
  templateUrl: './manage-tourney.component.html',
  styleUrls: ['./manage-tourney.component.scss']
})
export class ManageTourneyComponent implements OnInit {

  tourneyId!:number;
  tourney!: Tourney;

  newPlayer: Player = new Player();

  constructor(private route: ActivatedRoute, private tService: TourneyService) { }

  ngOnInit(): void {

    this.route.url.subscribe(data => {
      this.tourneyId = Number(data[2].path);
      console.log(this.tourneyId)
    })


    this.tService.getTourneyByTouneyId(this.tourneyId).subscribe(data =>{
      this.tourney = data;
      console.log(data)
    })
  }




  addPlayer(): void {
    this.tService.getUserByUsername(this.newPlayer.username).subscribe(data => {
      this.newPlayer.playerId = data.playerId;
    })


    setTimeout(()=> {
      this.tService.addTPtoTourney(this.newPlayer.playerId, this.tourneyId).subscribe(data => {
        console.log(data);
        window.location.reload();
      }, error => {
        alert("Cannot find a user with that username.");
      }
      )
    }, 500)
  }

}
