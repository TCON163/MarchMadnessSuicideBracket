package com.tcontechco.Tourney.controllers;

import com.tcontechco.Tourney.DTOs.RegisterPlayerDTO;
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
    public ResponseEntity<List<Player>> getAllPlayer(){
        return ResponseEntity.ok(service.getAll());}

    @GetMapping("/players/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Integer id){
        return ResponseEntity.ok(service.getPlayerById(id));
    }

    @PostMapping("/players")
    public ResponseEntity<Player> createOrSavePlayer(@RequestBody RegisterPlayerDTO player){
        Player p = new Player();
        p.setFirstName(player.getFirstName());
        p.setLastName(player.getLastName());
        p.setUsername(player.getUsername());
        p.setEmail(player.getEmail());
        p.setPassword(player.getPassword());
        Player person = service.createPlayer(p);
        if(person.getPlayerId()!= null){
            return ResponseEntity.ok(person);
        }


        return ResponseEntity.badRequest().body(person);

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


}
