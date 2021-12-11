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
        return repo.findAll().stream().filter( picks -> picks.getPlayer().getTPId().equals(id)).collect(Collectors.toList());
    }

    public Picks createOrUpdatePicks(Picks picks){
        return repo.save(picks);
    }

    public Picks setLoser(Picks picks){
        picks.setWinner(false);
        return repo.save(picks);
    }

    public Picks setWinner(Picks picks){
        picks.setWinner(true);
        return repo.save(picks);
    }


}
