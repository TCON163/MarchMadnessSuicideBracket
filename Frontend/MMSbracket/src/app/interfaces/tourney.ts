import { Time } from "@angular/common";
import { Admin, Player } from "./auth";

export class Team {
teamId!: number;
teamName!: string;
alive!: boolean;
}

export class Game {
    gameId!: number;
    date!: Date;
    time!: Time;
    home!: Team;
    away!: Team;
    completed!: boolean;
    winner!: Team;
}


export class Picks {
    pickId!: number;
    pick!: Team;
    player!: TPlayer;
    winner!: boolean;
    game!: Game;

}

export class TPlayer{
    tpid!: number;
    player!: Player;
    alive!: boolean;
    tpPicks!: Picks[];
}



export class Tourney{
    tourneyId!: number;
    title!: string;
    headGuy!: Admin;
    players!: TPlayer[];


}