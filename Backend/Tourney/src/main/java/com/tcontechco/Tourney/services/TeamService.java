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

    public Team getTeamById(Integer id) { return repo.findById(id).orElseThrow(() -> new ResourceDoesNotExist("No Team"));}

    public Team createTeam(Team team){ return repo.save(team);}

    public void killTeam(Team team) {
        team.setAlive(false);
        repo.save(team);
    }

    public void deleteTeam(Team team) { repo.delete(team);}
}
