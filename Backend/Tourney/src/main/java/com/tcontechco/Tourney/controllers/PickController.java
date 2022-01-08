package com.tcontechco.Tourney.controllers;

import com.tcontechco.Tourney.models.Game;
import com.tcontechco.Tourney.models.Picks;
import com.tcontechco.Tourney.models.TPlayer;
import com.tcontechco.Tourney.models.Team;
import com.tcontechco.Tourney.services.GameService;
import com.tcontechco.Tourney.services.PickServices;
import com.tcontechco.Tourney.services.TeamService;
import com.tcontechco.Tourney.services.TourneyPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PickController {
    private final PickServices services;
    private final TourneyPlayerService tpService;
    private final TeamService teamService;
    private final GameService gameService;

    @Autowired
    public PickController(PickServices services, TourneyPlayerService tpService, TeamService teamService, GameService gameService){
        this.services = services;
        this.tpService = tpService;
        this.gameService = gameService;
        this.teamService = teamService;
    }

    @CrossOrigin
    @GetMapping("/picks")
    public ResponseEntity<List<Picks>> getAllPicks(){return ResponseEntity.ok(services.getAllPicks());}

    @CrossOrigin
    @GetMapping("/picks/{id}")
    public ResponseEntity<List<Picks>> getAllPicksOfPlayerId(@PathVariable Integer id){
        return ResponseEntity.ok(services.getAllPicksWithPlayerId(id));
    }

    @CrossOrigin
    @PostMapping("/picks/{tplayerId}/team/{teamId}/game/{gameId}/day/{gameDay}")
    public ResponseEntity<Picks> createPick(@RequestBody Picks picks, @PathVariable Integer tplayerId, @PathVariable Integer teamId, @PathVariable Integer gameId,@PathVariable Integer gameDay){
        TPlayer tPlayer = tpService.findById(tplayerId);
        Game game = gameService.findById(gameId);
        Team team = teamService.getTeamById(teamId);
        picks.setPick(team);
        picks.setGame(game);
        picks.setPlayer(tPlayer);

        List<Picks> picksList= tPlayer.getTpPicks();
        try{
            Picks originalPick = picksList.get(gameDay);



            services.delete(originalPick);
            tPlayer.getTpPicks().remove(originalPick);
            tPlayer.getTpPicks().add(picks);
        }catch (IndexOutOfBoundsException e){
            picksList.add(picks);
            picks = services.createOrUpdatePicks(picks);
        }
        tpService.createTP(tPlayer);
        return ResponseEntity.ok(services.createOrUpdatePicks(picks));
    }

    @CrossOrigin
    @PutMapping("/picks/winner")
    public ResponseEntity<Picks> setPickAsWinner(@RequestBody Picks picks){
        return ResponseEntity.ok(services.setWinner(picks));
    }

    @CrossOrigin
    @PutMapping("/picks/loser")
    public ResponseEntity<Picks> setPickAsLoser(@RequestBody Picks picks){
        return ResponseEntity.ok(services.setLoser(picks));
    }
}
