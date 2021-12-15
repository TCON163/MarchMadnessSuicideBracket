import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Tourney } from 'src/app/interfaces/tourney';
import { TourneyService } from 'src/app/tourney.service';

@Component({
  selector: 'app-tourney',
  templateUrl: './tourney.component.html',
  styleUrls: ['./tourney.component.scss']
})
export class TourneyComponent implements OnInit {

tourneyId!: number;

tourney!: Tourney;

  constructor(private route: ActivatedRoute, private tService: TourneyService) { }

  ngOnInit(): void {
this.route.url.subscribe(data => {
  this.tourneyId = Number(data[1].toString())
})

this.tService.getTourneyByTouneyId(this.tourneyId).subscribe(data => {
  this.tourney = data;
})

  }

}
