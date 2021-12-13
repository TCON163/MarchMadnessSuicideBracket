package com.tcontechco.Tourney.controllers;

import com.tcontechco.Tourney.DTOs.LoginCredentialsDTO;
import com.tcontechco.Tourney.models.Player;
import com.tcontechco.Tourney.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AuthorizationController {

    private PlayerService service;

    @Autowired
    public AuthorizationController(PlayerService service){
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<Player> login(@RequestBody LoginCredentialsDTO login){
        Player player = service.login(login);
        if(player.getPlayerId() != null){
            return ResponseEntity.ok(player);
        }
        return ResponseEntity.badRequest().body(player);
    }
}
