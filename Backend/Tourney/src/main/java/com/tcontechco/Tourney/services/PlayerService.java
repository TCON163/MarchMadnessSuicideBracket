package com.tcontechco.Tourney.services;

import com.tcontechco.Tourney.DTOs.LoginCredentialsDTO;
import com.tcontechco.Tourney.DTOs.RegisterPlayerDTO;
import com.tcontechco.Tourney.exceptions.ResourceDoesNotExist;
import com.tcontechco.Tourney.models.Player;
import com.tcontechco.Tourney.models.TPlayer;
import com.tcontechco.Tourney.models.Tourney;
import com.tcontechco.Tourney.repos.PlayerRepo;
import com.tcontechco.Tourney.utils.CurrentUser;
import org.mariadb.jdbc.internal.util.dao.QueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlayerService implements UserDetailsService {

    private final PlayerRepo repo;
    private final PasswordEncoder encoder;


    @Autowired
    public PlayerService(PlayerRepo repo){
        this.repo = repo;
        encoder = new BCryptPasswordEncoder();
    }


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

    @Override
    public UserDetails loadUserByUsername(String username) throws ResourceDoesNotExist{
        try {
            Player player = repo.findByUsername(username).orElseThrow(()-> new ResourceDoesNotExist("Player not found with username: " + username));
            CurrentUser.setPlayer(player);
            return new User(player.getUsername(),player.getPassword(),new ArrayList<>());
        }catch (Exception e){
            throw new ResourceDoesNotExist("Player not found with username: " + username);
        }
    }



    public Player createPlayer(RegisterPlayerDTO player){
        Player p = new Player(player);
        p.setPassword(encoder.encode(p.getPassword()));
        return repo.save(p);
    }

    public Player createPlayer(Player player){
        return repo.save(player);
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

    public Set<TPlayer> getTPfromId(Integer id){
        return repo.getById(id).getTPlayers();

    }

    public List<Tourney> getTourneyByPlayerId(Integer id){
        Set<TPlayer> set = repo.getById(id).getTPlayers();
        return set.stream().map(TPlayer::getTourney).collect(Collectors.toList());
    }



}
