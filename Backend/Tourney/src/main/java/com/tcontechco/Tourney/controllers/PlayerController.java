package com.tcontechco.Tourney.controllers;

import com.tcontechco.Tourney.DTOs.RegisterPlayerDTO;
import com.tcontechco.Tourney.models.Player;
import com.tcontechco.Tourney.models.TPlayer;
import com.tcontechco.Tourney.models.Tourney;
import com.tcontechco.Tourney.services.PlayerService;
import com.tcontechco.Tourney.utils.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200", exposedHeaders = "Authorization")
@RestController
@RequestMapping("/api/v1")
public class PlayerController {
    private final PlayerService service;

    @Autowired
    public PlayerController(PlayerService service){this.service = service;}


    @GetMapping("/players")
    public ResponseEntity<List<Player>> getAllPlayer(){
        return ResponseEntity.ok(service.getAll());}

    @GetMapping("/players/currentUser")
    public ResponseEntity<Player> getCurrentUser(){
        return ResponseEntity.ok(CurrentUser.getPlayer());
    }


    @GetMapping("/players/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Integer id){
        return ResponseEntity.ok(service.getPlayerById(id));
    }




    @DeleteMapping("/players")
    public ResponseEntity<String> deletePlayer(@RequestBody Player player){
        service.deletePlayer(player);
        return ResponseEntity.ok(player.getUsername() + " was deleted");
    }


    @GetMapping("/players/username/{username}")
    public ResponseEntity<Player> getByUsername(@PathVariable String username){
        return ResponseEntity.ok(service.getPlayerByUsername(username));
    }


    @GetMapping("/players/tps/{id}")
    public ResponseEntity<Set<TPlayer>> getTPforPlayer(@PathVariable Integer id){
        return ResponseEntity.ok(service.getTPfromId(id));
    }


    @GetMapping("/players/tourney/{id}")
    public ResponseEntity<List<Tourney>> getTourneyByPlayerId(@PathVariable Integer id){
        return ResponseEntity.ok(service.getTourneyByPlayerId(id));
    }


}
