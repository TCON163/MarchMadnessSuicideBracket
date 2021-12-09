package com.tcontechco.User.controllers;


import com.tcontechco.User.models.Player;
import com.tcontechco.User.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/Player")
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService){
        this.playerService = playerService;
    }

    @GetMapping(value = "/players")
    public ResponseEntity<List<Player>> getAllPlayers() {
        List<Player> all = playerService.allPlayers();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/players/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Integer id){
        Player player = playerService.findByID(id);
        return ResponseEntity.ok(player);
    }

    @DeleteMapping("/players")
    public ResponseEntity<String> deletePlayer(@RequestBody Player player){
        playerService.deletePlayer(player);
        return ResponseEntity.ok().body(player.getUsername() + " was deleted.");
    }

    @PostMapping("/players")
    public ResponseEntity<Player> createPlayer(@RequestBody Player player){
        Player play = playerService.createOrSavePlayer(player);
        return ResponseEntity.ok(play);
    }

    @PutMapping("/players")
    public ResponseEntity<Player> updatePlayer(@RequestBody Player player) {
        Player updated = playerService.createOrSavePlayer(player);
        return ResponseEntity.ok(updated);
    }

}
