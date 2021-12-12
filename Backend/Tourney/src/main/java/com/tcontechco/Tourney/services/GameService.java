package com.tcontechco.Tourney.services;

import com.tcontechco.Tourney.exceptions.ResourceDoesNotExist;
import com.tcontechco.Tourney.models.Game;
import com.tcontechco.Tourney.repos.GameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    private final GameRepo gameRepo;

    @Autowired
    public GameService(GameRepo gameRepo){
        this.gameRepo = gameRepo;
    }

    public List<Game> getAll(){
       return gameRepo.findAll();
    }

    public Game findById(Integer id){
        return gameRepo.findById(id).orElseThrow(()-> new ResourceDoesNotExist("Game does not exist."));
    }

    public Game createOrSave(Game game){
        return gameRepo.save(game);
    }

    public void delete(Game game){
        gameRepo.delete(game);
    }
}
