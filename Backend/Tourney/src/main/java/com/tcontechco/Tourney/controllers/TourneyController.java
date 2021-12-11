package com.tcontechco.Tourney.controllers;

import com.tcontechco.Tourney.models.*;

import com.tcontechco.Tourney.services.AdminService;
import com.tcontechco.Tourney.services.PlayerService;
import com.tcontechco.Tourney.services.TourneyPlayerService;
import com.tcontechco.Tourney.services.TourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1")
public class TourneyController {
    //variables
    private final TourneyService service;
    private final PlayerService playerService;
    private final TourneyPlayerService tpService;
    private final AdminService adminService;



    //Constructor
    @Autowired
    public TourneyController(TourneyService service, PlayerService playerService, TourneyPlayerService tpService, AdminService adminService){
        this.service = service;
        this.playerService=playerService;
        this.tpService=tpService;
        this.adminService=adminService;
    }


    //Get all tourney
    @GetMapping("/tourney")
    public ResponseEntity<List<Tourney>> getAllTourney(){return ResponseEntity.ok(service.allTourney());}


    //Get tourney by ID
    @GetMapping("/tourney/{id}")
    public ResponseEntity<Tourney> getTourneyById(@PathVariable Integer id){
        return ResponseEntity.ok(service.getTourneyById(id));
    }


    //Create a tourney
    @PostMapping("/tourney")
    public ResponseEntity<Tourney> createTourney(@RequestBody Tourney tourney){

        return ResponseEntity.ok(service.createOrSaveTourney(tourney));
    }

    //Get all active TourneyPlayers for a tourney.
    @GetMapping("/tourney/{id}/activeTP")
    public ResponseEntity<Set<TPlayer>> getListOfTpFromTourneyId(@PathVariable Integer id){
        return ResponseEntity.ok(service.getActiveTPbyTourneyID(id));
    }

    //Add a TourneyPlayer to the Tourney
    @GetMapping("/tourney/{id}/tp/{playerId}")
    public ResponseEntity<TPlayer> addTPtoTourney(@PathVariable Integer id,@PathVariable Integer playerId){
        Tourney tourney = service.getTourneyById(id);

        TPlayer p = tpService.findById(playerId); ;





        tourney.enrollTPlayer(p);
        service.createOrSaveTourney(tourney);
        return ResponseEntity.ok(p);

    }
}
