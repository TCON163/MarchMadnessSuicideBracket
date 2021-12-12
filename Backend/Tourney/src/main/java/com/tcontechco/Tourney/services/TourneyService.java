package com.tcontechco.Tourney.services;

import com.tcontechco.Tourney.exceptions.ResourceDoesNotExist;
import com.tcontechco.Tourney.models.Player;
import com.tcontechco.Tourney.models.TPlayer;
import com.tcontechco.Tourney.models.Tourney;

import com.tcontechco.Tourney.repos.TourneyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service

public class TourneyService {
    private final TourneyRepo tourneyRepo;

    @Autowired
    public  TourneyService(TourneyRepo tourneyRepo) { this.tourneyRepo = tourneyRepo;}


    public List<Tourney> allTourney() { return tourneyRepo.findAll();}

    public Tourney getTourneyById(Integer id){

        return tourneyRepo.findById(id).orElseThrow(() -> new ResourceDoesNotExist("no Tourney with that Id"));
    }

    public Tourney createOrSaveTourney(Tourney tourney){ return tourneyRepo.saveAndFlush(tourney);}

    public Set<TPlayer> getActiveTPbyTourneyID(Integer id) {
        Tourney t = tourneyRepo.findById(id).orElseThrow(() -> new ResourceDoesNotExist("No Tourney with that ID"));

        return t.getPlayers().stream().filter(tPlayer -> tPlayer.getAlive().equals(true)).collect(Collectors.toSet());
    }




}
