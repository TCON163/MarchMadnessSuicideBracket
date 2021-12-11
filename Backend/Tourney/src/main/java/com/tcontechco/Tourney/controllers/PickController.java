package com.tcontechco.Tourney.controllers;

import com.tcontechco.Tourney.models.Picks;
import com.tcontechco.Tourney.services.PickServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PickController {
    private final PickServices services;

    @Autowired
    public PickController(PickServices services){ this.services = services;}

    @GetMapping("/picks")
    public ResponseEntity<List<Picks>> getAllPicks(){return ResponseEntity.ok(services.getAllPicks());}

    @GetMapping("/picks/{id}")
    public ResponseEntity<List<Picks>> getAllPicksOfPlayerId(@PathVariable Integer id){
        return ResponseEntity.ok(services.getAllPicksWithPlayerId(id));
    }

    @PostMapping("/picks")
    public ResponseEntity<Picks> createPick(@RequestBody Picks picks){
        return ResponseEntity.ok(services.createOrUpdatePicks(picks));
    }

    @PutMapping("/picks/winner")
    public ResponseEntity<Picks> setPickAsWinner(@RequestBody Picks picks){
        return ResponseEntity.ok(services.setWinner(picks));
    }

    @PutMapping("/picks/loser")
    public ResponseEntity<Picks> setPickAsLoser(@RequestBody Picks picks){
        return ResponseEntity.ok(services.setLoser(picks));
    }
}
