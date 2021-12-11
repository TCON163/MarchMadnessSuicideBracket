package com.tcontechco.Tourney.controllers;

import com.tcontechco.Tourney.models.Player;
import com.tcontechco.Tourney.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class PlayerController {
    private final PlayerService service;

    @Autowired
    public PlayerController(PlayerService service){this.service = service;}

    @GetMapping("/players")
    public ResponseEntity<List<Player>> getAllPlayer(){return ResponseEntity.ok(service.getAll());}

    @GetMapping("/players/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Integer id){
        return ResponseEntity.ok(service.getPlayerById(id));
    }

    @PostMapping("/players")
    public ResponseEntity<Player> createOrSavePlayer(@RequestBody Player player){
        return ResponseEntity.ok(service.createPlayer(player));
    }

    @DeleteMapping("/players")
    public ResponseEntity<String> deletePlayer(@RequestBody Player player){
        service.deletePlayer(player);
        return ResponseEntity.ok(player.getUsername() + " was deleted");
    }


}
