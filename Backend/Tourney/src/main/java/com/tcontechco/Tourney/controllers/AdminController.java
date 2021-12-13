package com.tcontechco.Tourney.controllers;

import com.tcontechco.Tourney.models.Admin;
import com.tcontechco.Tourney.models.Player;
import com.tcontechco.Tourney.services.AdminService;
import com.tcontechco.Tourney.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AdminController {
    private final AdminService adminService;
    private final PlayerService playerService;

    @Autowired
    public AdminController(AdminService adminService, PlayerService playerService){ this.adminService=adminService;this.playerService=playerService;}


    @GetMapping("/admin")
    public ResponseEntity<List<Admin>> getAll() {
        return ResponseEntity.ok(adminService.getAll());
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<Admin> getAdminBy(@PathVariable Integer id){
        return ResponseEntity.ok(adminService.getByID(id));
    }

    @PostMapping("/admin/{playerId}")
    public ResponseEntity<Admin> createAdmin(@PathVariable Integer playerId){

        Player p = playerService.getById(playerId);
        Admin a = new Admin();

        a.setPlayer(p);

        playerService.createPlayer(p);

        return ResponseEntity.ok(adminService.createAdmin(a));
    }
}
