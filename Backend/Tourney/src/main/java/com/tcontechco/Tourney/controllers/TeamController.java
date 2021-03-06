package com.tcontechco.Tourney.controllers;

import com.tcontechco.Tourney.models.Team;
import com.tcontechco.Tourney.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TeamController {
    private final TeamService service;

    @Autowired
    public TeamController(TeamService service){this.service = service;}

    @CrossOrigin
    @GetMapping("/teams")
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> list = service.allTeams();
        return ResponseEntity.ok(list);
    }

    @CrossOrigin
    @GetMapping("/teams/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable Integer id){
        Team team = service.getTeamById(id);
        return ResponseEntity.ok(team);
    }

    @CrossOrigin
    @PostMapping("/teams")
    public ResponseEntity<Team> createTeam(@RequestBody Team team){
        Team t = service.createTeam(team);
        return ResponseEntity.ok(t);
    }

    @CrossOrigin
    @PutMapping("/teams/{id}/kill")
    public ResponseEntity<Team> killTeam(@RequestBody Team team){
        service.killTeam(team);
        return ResponseEntity.ok(team);
    }

    @CrossOrigin
    @DeleteMapping("/teams")
    public ResponseEntity<String> deleteTeam(@RequestBody Team team) {
        service.deleteTeam(team);
        return ResponseEntity.ok(team.getTeamName()+" was deleted.");
    }

    @CrossOrigin
    @GetMapping("/teams/fillDB")
    public ResponseEntity<String> fillDB(){
        service.fillDB();
        return ResponseEntity.ok("db filled");
    }

    @CrossOrigin
    @GetMapping("/teams/alive")
    public ResponseEntity<List<Team>> aliveTeam(){
        return ResponseEntity.ok(service.allTeamsStillAlive());
    }

    @CrossOrigin
    @GetMapping("/teams/eliminated")
    public ResponseEntity<List<Team>> eliminatedTeams(){
        return ResponseEntity.ok(service.teamsEliminated());
    }


}
