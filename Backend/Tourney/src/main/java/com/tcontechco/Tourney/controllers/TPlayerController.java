package com.tcontechco.Tourney.controllers;

import com.tcontechco.Tourney.models.Picks;
import com.tcontechco.Tourney.models.TPlayer;
import com.tcontechco.Tourney.services.TourneyPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1")
public class TPlayerController {
    private final TourneyPlayerService service;

    @Autowired
    public TPlayerController(TourneyPlayerService service){ this.service = service;}

    @GetMapping("/tp")
    public ResponseEntity<List<TPlayer>>  getAllTP(){return ResponseEntity.ok(service.getAllTourneyPlayers());}

    @GetMapping("/tp/tourney/{id}")
    public ResponseEntity<Set<TPlayer>> getTpByTourneyId(@PathVariable Integer id){
        return ResponseEntity.ok(service.getTPwithTourneyId(id));
    }

    @PostMapping("/tp")
    public ResponseEntity<TPlayer> createTP(@RequestBody TPlayer tp){return ResponseEntity.ok(service.createTP(tp));}

//    @GetMapping("/tp/picks/{id}")
//    public ResponseEntity<List<Picks>> getPicksByTpId(@PathVariable Integer id){
//        return ResponseEntity.ok(service.picksByTPID(id));
//    }

    @GetMapping("/tp/kill/{id}")
    public ResponseEntity<TPlayer> killPlayerById(@PathVariable Integer id){
        TPlayer tp = service.findById(id);
        return ResponseEntity.ok(service.setTerminated(tp));
    }


}
