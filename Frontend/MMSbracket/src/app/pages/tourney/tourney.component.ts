import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DateDTO, Game, Picks, Team, Tourney, TPlayer } from 'src/app/interfaces/tourney';
import { TourneyService } from 'src/app/tourney.service';

@Component({
  selector: 'app-tourney',
  templateUrl: './tourney.component.html',
  styleUrls: ['./tourney.component.scss']
})
export class TourneyComponent implements OnInit {

tourneyId!: number;
dayOfTourney!: number;
pickSet = false;
date!: DateDTO;
gameList!: Game[];
teamList: Team[] = [];
tourney!: Tourney;

currentTPlayer!: TPlayer;
teamUsedIdList: number[] = [];

  constructor(private route: ActivatedRoute, private tService: TourneyService) { }

  ngOnInit(): void {
      this.route.url.subscribe(data => {
        this.tourneyId = Number(data[1].toString())
      })

      this.tService.getTourneyByTouneyId(this.tourneyId).subscribe(data => {
        this.tourney = data;
        console.log(data)
        this.currentTPlayer = data.players.filter(tp => tp.player.username === localStorage.getItem("username"))[0];
        this.teamUsedIdList = data.players.filter(tp => tp.player.username === localStorage.getItem("username"))[0].tpPicks.map(picks => picks.pick.teamId)
        console.log(this.currentTPlayer)
      })

        //Setting the day of the Tournament.
        let currentTime = Date.now();
        let gameDate1 = new Date("2021-12-20T00:00:00").getTime();
        let gameDate2 = new Date("2021-12-20T00:00:00").getTime();
        let gameDate3 = new Date("2021-12-17T00:00:00").getTime();
        let gameDate4 = new Date("2021-12-18T00:00:00").getTime();

              if(currentTime <gameDate1){
                this.dayOfTourney = 0;
                this.date = new DateDTO();
                this.date.year = 122;
                this.date.month = 2;
                this.date.day = 17;
              } else if(currentTime < gameDate2){
                this.dayOfTourney = 1;
                this.date = new DateDTO();
                this.date.year = 122;
                this.date.month = 2;
                this.date.day = 18;
              } else if(currentTime < gameDate3){
                this.dayOfTourney = 2;
                this.date = new DateDTO();
                this.date.year = 122;
                this.date.month = 2;
                this.date.day = 19;
              } else if(currentTime< gameDate4) {
                this.dayOfTourney = 3;
                this.date = new DateDTO();
                this.date.year = 122;
                this.date.month = 2;
                this.date.day = 20;
              } else {
                this.dayOfTourney =4
              }

              this.tService.getListOfGamesByDate(this.date).subscribe(data => {
                this.gameList = data;
                data.forEach(game => {
                    if(!this.teamUsedIdList.includes(game.home.teamId)){
                      this.teamList.push(game.home)
                    }
                    if(!this.teamUsedIdList.includes(game.away.teamId)){
                      this.teamList.push(game.away)
                    }
                  })
              })

          this.teamList.sort()

          console.log(this.teamList)

  }//end of ngOnInit


  doesPlayerHavePick(tp: TPlayer): boolean {
    if(tp.tpPicks.length > this.dayOfTourney){
      return true;
    }
    return false;
  }

  isCurrentUser(tp: TPlayer): boolean {
    if(tp.player.username === localStorage.getItem("username")){
      return true;
    }
    return false;
  }

  removeTeam(rTeam: Team){
    this.teamList.forEach((team, index)=> {
      if(team === rTeam) this.teamList.splice(index, 1);
    });
  }

}
