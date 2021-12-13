package com.tcontechco.Tourney.services;

import com.tcontechco.Tourney.DTOs.LoginCredentialsDTO;
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
       Player player = repo.findById(id).orElseThrow(()-> new ResourceDoesNotExist("Player does not exist."));
       return new Player(player);

    }

    public Player getPlayerByUsername(String username){

            Player player = repo.findByUsername(username).orElseThrow(()-> new ResourceDoesNotExist("Cannot find player with username: " + username));

            return new Player(player);
    }

    public Player createPlayer(Player player){
        Player p = repo.save(player);
        return new Player(p);
    }

    public void deletePlayer(Player player){
        repo.delete(player);
    }

    public Player login(LoginCredentialsDTO log){
        Player player = repo.findByUsername(log.getUsername()).orElseThrow(()-> new ResourceDoesNotExist("cant find username"));
        if (player.getPassword().equals(log.getPassword())){
            return new Player(player);
        }
        return new Player();
    }

}
