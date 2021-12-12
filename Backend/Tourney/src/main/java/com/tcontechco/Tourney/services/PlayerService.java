package com.tcontechco.Tourney.services;

import com.tcontechco.Tourney.exceptions.ResourceDoesNotExist;
import com.tcontechco.Tourney.models.Player;
import com.tcontechco.Tourney.repos.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PlayerService {

    private final PlayerRepo repo;

    @Autowired
    public PlayerService(PlayerRepo repo){this.repo = repo;}


    public List<Player> getAll() {return repo.findAll();}

    public Player getPlayerById(Integer id){
        return repo.findById(id).orElseThrow(()-> new ResourceDoesNotExist("Player does not exist."));
    }

    public Player createPlayer(Player player){
        return repo.save(player);
    }

    public void deletePlayer(Player player){
        repo.delete(player);
    }

}
