package com.tcontechco.Tourney.controllers;

import com.tcontechco.Tourney.models.Game;
import com.tcontechco.Tourney.models.Team;
import com.tcontechco.Tourney.services.GameService;
import com.tcontechco.Tourney.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class GameController {
    private final GameService gameService;
    private final TeamService teamService;

    @Autowired
    public GameController(GameService gameService, TeamService teamService){
        this.gameService = gameService;
        this.teamService = teamService;
    }

    @GetMapping("/games")
    public ResponseEntity<List<Game>> getAll(){
        return ResponseEntity.ok(gameService.getAll());
    }

    @GetMapping("/games/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Integer id){
        return ResponseEntity.ok(gameService.findById(id));
    }

    @PostMapping("/games/home/{homeId}/Away/{awayId}")
    public ResponseEntity<Game> createGame(@RequestBody Game game, @PathVariable Integer homeId, @PathVariable Integer awayId){
        Team home = teamService.getTeamById(homeId);
        Team away = teamService.getTeamById(awayId);
        game.setHome(home);
        game.setAway(away);


        return ResponseEntity.ok(gameService.createOrSave(game));
    }

    @GetMapping("/games/fill32")
    public ResponseEntity<String> fillDB(){
        gameService.fill32();
        return ResponseEntity.ok("game db filled.");
    }

    @GetMapping("/games/fill2ndRound")
    public ResponseEntity<String> fill2ndround(){
        gameService.fill33to48();
        return ResponseEntity.ok("2nd round of games are in the database.");
    }

    @GetMapping("/games/fillSweet16")
    public ResponseEntity<String> fillSweet16(){
        gameService.fillSweet16();
        return ResponseEntity.ok("Sweet 16 are in the database.");
    }

    @GetMapping("/games/fillElite8")
    public ResponseEntity<String> fillElite8(){
        gameService.fillElite8();
        return ResponseEntity.ok("Elite 8 are in the database.");
    }

    @GetMapping("/games/fillFinal4")
    public ResponseEntity<String> fillFinal4(){
        gameService.fillFinalFour();
        return ResponseEntity.ok("Final 4 are in the database.");
    }

    @GetMapping("/games/fillChamp")
    public ResponseEntity<String> fillChamp(){
        gameService.fillChamp();
        return ResponseEntity.ok("Champ game is in the database.");
    }

    @GetMapping("/games/simulateFirstRound")
    public ResponseEntity<String> simulateFirst(){
        gameService.simulate1stRound();
        return ResponseEntity.ok("simulated 1st round");
    }

    @GetMapping("/games/simulateSecondRound")
    public ResponseEntity<String> simulateSecond(){
        gameService.simulate2ndRound();
        return ResponseEntity.ok("simulated 2nd round");
    }

    @GetMapping("/games/simulateSweet16")
    public ResponseEntity<String> simulateSweet16(){
        gameService.simulateSweet16();
        return ResponseEntity.ok("simulated Sweet16");
    }

    @GetMapping("/games/simulateElite8")
    public ResponseEntity<String> simulateElite8(){
        gameService.simulateElite8();
        return ResponseEntity.ok("simulated Elite8");
    }
    @GetMapping("/games/simulateFinal4")
    public ResponseEntity<String> simulateFinal4(){
        gameService.simulateFinalFour();
        return ResponseEntity.ok("simulated Final4");
    }

    @GetMapping("/games/simulateChamp")
    public ResponseEntity<String> simulateChamp(){
        gameService.simulateChamp();
        return ResponseEntity.ok("simulated Champ");
    }

    @PutMapping("/games/{gameId}/winner/{teamId}")
    public ResponseEntity<Game> winner(@PathVariable Integer gameId, @PathVariable Integer teamId){
        Game game = gameService.findById(gameId);
        if (game.getHome().getTeamId().equals(teamId)){
            Team winner = teamService.getTeamById(teamId);
            Team away = game.getAway();
            away.setAlive(false);
            teamService.createTeam(away);
            game.setWinner(winner);
            game.setCompleted(true);
            return ResponseEntity.ok(gameService.createOrSave(game));
        } else if(game.getAway().getTeamId().equals(teamId)){
            Team winner = game.getAway();
            Team home = game.getHome();
            home.setAlive(false);
            teamService.createTeam(home);
            game.setWinner(winner);
            game.setCompleted(true);
            return ResponseEntity.ok(gameService.createOrSave(game));
        }

        Game nulls = new Game();
        return ResponseEntity.badRequest().body(nulls);
    }

    @GetMapping("/games/completed")
    public ResponseEntity<List<Game>> completedGames(){
        return ResponseEntity.ok(gameService.getCompletedGames());
    }

    @GetMapping("/games/uncompleted")
    public ResponseEntity<List<Game>> uncompletedGames(){
        return ResponseEntity.ok(gameService.getGamesNotCompleted());
    }
}
