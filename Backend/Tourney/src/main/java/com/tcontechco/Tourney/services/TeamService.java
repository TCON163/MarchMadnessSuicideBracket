package com.tcontechco.Tourney.services;

import com.tcontechco.Tourney.exceptions.ResourceDoesNotExist;
import com.tcontechco.Tourney.models.Team;
import com.tcontechco.Tourney.repos.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TeamService {
    private final TeamRepo repo;

    @Autowired
    public TeamService(TeamRepo repo) { this.repo = repo;}

    public List<Team> allTeams(){ return repo.findAll();}

    public List<Team> allTeamsStillAlive(){
        return repo.findAll().stream().filter(t -> t.getAlive().equals(true)).collect(Collectors.toList());
    }

    public List<Team> teamsEliminated(){
        return repo.findAll().stream().filter(team -> team.getAlive().equals(false)).collect(Collectors.toList());
    }

    public Team getTeamById(Integer id) { return repo.findById(id).orElseThrow(() -> new ResourceDoesNotExist("No Team"));}

    public Team createTeam(Team team){ return repo.save(team);}

    public void killTeam(Team team) {
        team.setAlive(false);
        repo.save(team);
    }

    public void deleteTeam(Team team) { repo.delete(team);}


    public void fillDB(){
        for (int i = 0 ; i<16; i++){
            Team westTeam = new Team();
            westTeam.setTeamId(i+1);
            String name = "West Seed " + (i+1);
            westTeam.setTeamName(name);
            westTeam.setAlive(true);
            repo.save(westTeam);

        }
        for (int i = 0 ; i<16; i++){
            Team eastTeam = new Team();
            eastTeam.setTeamId(i+17);
            String name = "East Seed " + (i+1);
            eastTeam.setTeamName(name);
            eastTeam.setAlive(true);
            repo.save(eastTeam);

        }
        for (int i = 0 ; i<16; i++){
            Team southTeam = new Team();
            southTeam.setTeamId(i+33);
            String name = "South Seed " + (i+1);
            southTeam.setTeamName(name);
            southTeam.setAlive(true);
            repo.save(southTeam);

        }
        for (int i = 0 ; i<16; i++){
            Team team = new Team();
            team.setTeamId(i+49);
            String name = "MidWest Seed " + (i+1);
            team.setTeamName(name);
            team.setAlive(true);
            repo.save(team);

        }
    }


}
