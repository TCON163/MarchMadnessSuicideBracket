package com.tcontechco.Tourney.controllers;

import com.tcontechco.Tourney.DTOs.LoginCredentialsDTO;
import com.tcontechco.Tourney.DTOs.RegisterPlayerDTO;
import com.tcontechco.Tourney.exceptions.AuthenticationException;
import com.tcontechco.Tourney.models.Admin;
import com.tcontechco.Tourney.models.Player;
import com.tcontechco.Tourney.services.AdminService;
import com.tcontechco.Tourney.services.PlayerService;
import com.tcontechco.Tourney.utils.CurrentUser;
import com.tcontechco.Tourney.utils.JWTUtil;
import com.tcontechco.Tourney.utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;
import java.util.Currency;

@RestController
@RequestMapping("/api/v1")
public class AuthorizationController {

    private final PlayerService service;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final AdminService adminService;

    @Autowired
    public AuthorizationController(PlayerService service, AuthenticationManager authenticationManager, JWTUtil jwtUtil, AdminService adminService){
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.adminService = adminService;
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<Player> login(@RequestBody LoginCredentialsDTO login){
        String token = jwtUtil.getPrefix();
        if(authenticate(login.getUsername(),login.getPassword())){
            UserDetails userDetails = service.loadUserByUsername(login.getUsername());
            token += jwtUtil.createJWT(userDetails);
            Token.setToken(token);
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", token);
            headers.set("Access-Control-Expose-Headers", "*");
            return new ResponseEntity<>(CurrentUser.getPlayer(), headers, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
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


    public boolean authenticate(String username, String password){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return true;
        }catch (BadCredentialsException e){
            throw new AuthenticationException("These credentials are wrong! Have you tried caps lock?");
        }
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
