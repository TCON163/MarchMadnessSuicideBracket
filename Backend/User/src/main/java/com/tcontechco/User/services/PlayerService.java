package com.tcontechco.User.services;


import com.tcontechco.User.exceptions.PlayerDoesNotExistException;
import com.tcontechco.User.models.Player;
import com.tcontechco.User.repos.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PlayerService {
    private  PlayerRepo playerRepo;

    @Autowired
    public PlayerService(PlayerRepo playerRepo) { this.playerRepo = playerRepo;}

    public List<Player> allPlayers() { return playerRepo.findAll();}

    public Player findByID(Integer id) {
        return playerRepo.findById(id).orElseThrow(() -> new PlayerDoesNotExistException("Player id does not exist:" + id.toString()));
    }

    public Player createOrSavePlayer(Player player){
        return playerRepo.save(player);
    }

    public void deletePlayer(Player player){
        playerRepo.delete(player);
    }
}
