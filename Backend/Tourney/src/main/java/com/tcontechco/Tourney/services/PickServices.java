package com.tcontechco.Tourney.services;

import com.tcontechco.Tourney.models.Picks;
import com.tcontechco.Tourney.repos.PicksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PickServices {
    private final PicksRepo repo;

    @Autowired
    public PickServices(PicksRepo repo) {this.repo = repo;}

    public List<Picks> getAllPicks(){
        return repo.findAll();
    }

    public List<Picks> getAllPicksWithPlayerId(Integer id){
        return repo.findAll().stream().filter( picks -> picks.getTourneyPlayer().getTourneyPlayerId().equals(id) ).collect(Collectors.toList());
    }

    public Picks createPicks(Picks picks){
        return repo.save(picks);
    }

    //update Picks should come with some logic based on if the current team and new team hasn't starting playing yet.
}
