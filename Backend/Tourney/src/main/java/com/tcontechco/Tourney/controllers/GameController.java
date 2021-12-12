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
}
