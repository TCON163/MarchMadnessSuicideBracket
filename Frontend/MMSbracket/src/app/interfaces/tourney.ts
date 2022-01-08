import { Time } from "@angular/common";
import { Admin, Player } from "./auth";

export class Team {
teamId!: number;
teamName!: string;
alive!: boolean;
}

export class Game {
    gameId!: number;
    date!: String;
    time!: String;
    home!: Team;
    away!: Team;
    completed!: boolean;
    winner!: Team;
}

export class DateDTO {
    year!: number;
    month!: number;
    day!: number;
}


export class Picks {
    pickId!: number;
    pick!: Team;
    player!: TPlayer;
    winner!: boolean|null;
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
