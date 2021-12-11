package com.tcontechco.Tourney.controllers;

import com.tcontechco.Tourney.models.Player;
import com.tcontechco.Tourney.models.Tourney;
import com.tcontechco.Tourney.models.TourneyPlayer;
import com.tcontechco.Tourney.services.PlayerService;
import com.tcontechco.Tourney.services.TourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TourneyController {
    //variables
    private final TourneyService service;
    private final PlayerService playerService;


    //Constructor
    @Autowired
    public TourneyController(TourneyService service, PlayerService playerService){this.service = service; this.playerService=playerService;}


    //Get all tourney
    @GetMapping("/tourney")
    public ResponseEntity<List<Tourney>> getAllTourney(){return ResponseEntity.ok(service.allTourney());}

    @GetMapping("/tourney/{id}")
    public ResponseEntity<Tourney> getTourneyById(@PathVariable Integer id){
        return ResponseEntity.ok(service.getTourneyById(id));
    }

    @PostMapping("/tourney/{playerId}")
    public ResponseEntity<Tourney> createTourney(@PathVariable Integer playerId,@RequestBody Tourney tourney){
        Tourney t = new Tourney();
        Player p = playerService.getPlayerById(playerId);
        t.setTitle(tourney.getTitle());
        t.setAdmin(p);
        List<TourneyPlayer> list = new ArrayList<>();
        t.setPlayers(list);
        return ResponseEntity.ok(service.createOrSaveTourney(t));
    }

    @GetMapping("/tourney/{id}/activeTP")
    public ResponseEntity<List<TourneyPlayer>> getListOfTpFromTourneyId(@PathVariable Integer id){
        return ResponseEntity.ok(service.getActiveTPbyTourneyID(id));
    }
}
