package com.tcontechco.Tourney.services;

import com.tcontechco.Tourney.DTOs.LoginCredentialsDTO;
import com.tcontechco.Tourney.exceptions.ResourceDoesNotExist;
import com.tcontechco.Tourney.models.Player;
import com.tcontechco.Tourney.repos.PlayerRepo;
import org.mariadb.jdbc.internal.util.dao.QueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepo repo;

    @Autowired
    public PlayerService(PlayerRepo repo){this.repo = repo;}


    public List<Player> getAll() {
        List<Player> list =  repo.findAll();
        list.stream().forEach(player -> player.setPassword("Protected for you"));
        return list;
    }

    public Player getById(Integer id){return repo.findById(id).orElseThrow(()-> new ResourceDoesNotExist("Player does not exist"));}

    public Player getPlayerById(Integer id){
       return repo.findById(id).orElseThrow(()-> new ResourceDoesNotExist("Player does not exist."));


    }

    public Player getPlayerByUsername(String username){
            return repo.findByUsername(username).orElseThrow(()-> new ResourceDoesNotExist("Cannot find player with username: " + username));
    }

    public Player createPlayer(Player player){
        try{
            return repo.save(player);
        }catch (Exception e){

            Player failed = new Player();
            failed.setUsername("Username is already used");
            return failed;
        }
    }

    public void deletePlayer(Player player){
        repo.delete(player);
    }

    public Player login(LoginCredentialsDTO log){
        Player player = repo.findByUsername(log.getUsername()).orElseThrow(()-> new ResourceDoesNotExist("cant find username"));
        if (player.getPassword().equals(log.getPassword())){
            return player;
        }
        return new Player();
    }

}
