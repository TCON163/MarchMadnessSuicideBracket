import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Tourney, TPlayer } from 'src/app/interfaces/tourney';
import { TourneyService } from 'src/app/tourney.service';

@Component({
  selector: 'app-tourney-list',
  templateUrl: './tourney-list.component.html',
  styleUrls: ['./tourney-list.component.scss']
})
export class TourneyListComponent implements OnInit {

  tourneyList!: Tourney[];

  tPlayerList!: TPlayer[];

  dayOfTourney!: number;

  


  

  id: number = Number(localStorage.getItem("playerId"));



  constructor(private tourneyService: TourneyService, private router: Router) { }

  ngOnInit(): void {
    this.tourneyService.getListOfTourneyByPlayerId(this.id).subscribe(data => {
      setTimeout(()=>{
        this.tourneyList = data;
      },1000)
     
      console.log(data)
    }, error => console.log(error))


    this.tourneyService.getSetOfTPlayerByPlayerId(this.id).subscribe(data => {
      this.tPlayerList = data;
      console.log(data)
    }, error => console.log(error))



    //Setting the day of the Tournament.
    let currentTime = Date.now();
    let gameDate1 = new Date("2021-12-16T00:00:00").getTime();
    let gameDate2 = new Date("2021-12-16T00:00:00").getTime();
    let gameDate3 = new Date("2021-12-17T00:00:00").getTime();
    let gameDate4 = new Date("2021-12-18T00:00:00").getTime();

    if(currentTime <gameDate1){
      this.dayOfTourney = 0;
    } else if(currentTime < gameDate2){
      this.dayOfTourney = 1;
    } else if(currentTime < gameDate3){
      this.dayOfTourney = 2;
    } else if(currentTime< gameDate4) {
      this.dayOfTourney = 3;
    } else {
      this.dayOfTourney =4
    }


  }

// started a method to get the users TPlayer from the tourney
  selectUserTPlayer(tourney: Tourney): TPlayer {
    let tpidList = this.tPlayerList.map(tp => tp.tpid)

    return tourney.players.filter(player => tpidList.includes(player.tpid))[0]
  }



  needsToPick(tourney: Tourney): boolean{
    let tp = this.selectUserTPlayer(tourney);
    
    if(tp.alive){
      if(tp.tpPicks.length === this.dayOfTourney) {
        return true;
      }
    }
    return false;
  }


  doesNotNeedToPick(tourney:Tourney): boolean{
    let tp = this.selectUserTPlayer(tourney);

    if(tp.alive){
      if(tp.tpPicks.length > this.dayOfTourney){
        return true;
      }
    }
    return false;
  }

  getPick(tourney: Tourney): string {
    let tp = this.selectUserTPlayer(tourney);
    return tp.tpPicks[this.dayOfTourney].pick.teamName;
  }

  isAdmin(tourney: Tourney): boolean {
    if(tourney.headGuy.adminId === Number(localStorage.getItem("playerId"))){
      return true;
    }
    return false;
  }


  clickTourneyButton(id: number){

    this.router.navigate(["home/tourney", id])

  }





}
