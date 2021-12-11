package com.tcontechco.Tourney.services;

import com.tcontechco.Tourney.exceptions.ResourceDoesNotExist;
import com.tcontechco.Tourney.models.Picks;
import com.tcontechco.Tourney.models.TourneyPlayer;
import com.tcontechco.Tourney.repos.TourneyPlayerRepo;
import net.bytebuddy.pool.TypePool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TourneyPlayerService {

    private final TourneyPlayerRepo repo;

    @Autowired
    public TourneyPlayerService(TourneyPlayerRepo repo) { this.repo = repo;}

    public List<TourneyPlayer> getAllTourneyPlayers() {return repo.findAll();}

    public List<TourneyPlayer> getTPwithTourneyId(Integer id){
        return repo.findAll().stream().filter(t -> t.getTourney().getTourneyId().equals(id)).collect(Collectors.toList());
    }

    public TourneyPlayer findById(Integer id) {
        return repo.findById(id).orElseThrow(()-> new ResourceDoesNotExist("TourneyPlayer ID does not exist."));
    }

    public List<Picks> picksByTPID(Integer id){
        TourneyPlayer tp = repo.findById(id).orElseThrow(() -> new ResourceDoesNotExist("No TP with ID"));
        return tp.getPicks();
    }

    public TourneyPlayer createTP(TourneyPlayer tp) { return repo.save(tp);}

    public TourneyPlayer setTerminated(TourneyPlayer tp) {
        tp.setAlive(false);
        return repo.save(tp);
    }




}
