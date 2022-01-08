package com.tcontechco.Tourney.controllers;

import com.tcontechco.Tourney.DTOs.LoginCredentialsDTO;
import com.tcontechco.Tourney.DTOs.RegisterPlayerDTO;
import com.tcontechco.Tourney.jwt.UsernameAndPasswordAuthenticationRequest;
import com.tcontechco.Tourney.models.Admin;
import com.tcontechco.Tourney.models.Player;
import com.tcontechco.Tourney.services.AdminService;
import com.tcontechco.Tourney.services.PlayerService;
import com.tcontechco.Tourney.utils.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;

@RestController
@RequestMapping("/api/v1")
public class AuthorizationController {

    private final PlayerService service;


    private final AdminService adminService;

    @Autowired
    public AuthorizationController(PlayerService service,  AdminService adminService){
        this.service = service;


        this.adminService = adminService;
    }

    @CrossOrigin(origins = "http://localhost:4200",exposedHeaders = "Authorization")
    @PostMapping("/login")
    public ResponseEntity<Player> login(@RequestBody UsernameAndPasswordAuthenticationRequest login){

            Player player = service.getPlayerByUsername(login.getUsername());
            return  ResponseEntity.ok(player);

    }

    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<Player> savePlayer(@RequestBody RegisterPlayerDTO playerDTO){

        Player player = service.createPlayer(playerDTO);
        Admin admin = new Admin();
        admin.setPlayer(player);
        admin.setAdminId(player.getPlayerId());
        adminService.createAdmin(admin);



        return ResponseEntity.ok(player);
    }






    @GetMapping("/logout")
    public ResponseEntity<String> logout(){
        CurrentUser.setPlayer(null);
        SecurityContextHolder.clearContext();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Status", "Logout Successful!");
        return ResponseEntity.ok(jsonObject.toString());
    }
}
