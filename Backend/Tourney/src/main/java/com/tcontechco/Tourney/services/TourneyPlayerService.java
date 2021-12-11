package com.tcontechco.Tourney.services;

import com.tcontechco.Tourney.exceptions.ResourceDoesNotExist;
import com.tcontechco.Tourney.models.Picks;
import com.tcontechco.Tourney.models.TPlayer;

import com.tcontechco.Tourney.repos.TourneyPlayerRepo;
import com.tcontechco.Tourney.repos.TourneyRepo;
import net.bytebuddy.pool.TypePool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class TourneyPlayerService {

    private final TourneyPlayerRepo repo;
    private final TourneyRepo tourneyRepo;

    @Autowired
    public TourneyPlayerService(TourneyPlayerRepo repo, TourneyRepo tourneyRepo) {
        this.repo = repo;
        this.tourneyRepo=tourneyRepo;
    }

    public List<TPlayer> getAllTourneyPlayers() {return repo.findAll();}

    public Set<TPlayer> getTPwithTourneyId(Integer id){
        return tourneyRepo.findById(id).orElseThrow(()-> new ResourceDoesNotExist("Tourney not found")).getPlayers();
    }

    public TPlayer findById(Integer id) {
        return repo.findById(id).orElseThrow(()-> new ResourceDoesNotExist("TourneyPlayer ID does not exist."));
    }

//    public List<Picks> picksByTPID(Integer id){
//        TPlayer tp = repo.findById(id).orElseThrow(() -> new ResourceDoesNotExist("No TP with ID"));
//        return tp.getTpPicks();
//    }

    public TPlayer createTP(TPlayer tp) { return repo.save(tp);}

    public TPlayer setTerminated(TPlayer tp) {
        tp.setAlive(false);
        return repo.save(tp);
    }




}
