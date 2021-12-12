package com.tcontechco.Tourney.services;

import com.tcontechco.Tourney.exceptions.ResourceDoesNotExist;
import com.tcontechco.Tourney.models.Game;
import com.tcontechco.Tourney.models.Team;
import com.tcontechco.Tourney.repos.GameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Service
public class GameService {
    private final GameRepo gameRepo;
    private final TeamService teamService;

    @Autowired
    public GameService(GameRepo gameRepo, TeamService teamService){

        this.gameRepo = gameRepo;
        this.teamService = teamService;
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

    public void winner(Game game,Team team){
        game.setWinner(team);
        gameRepo.save(game);
    }

    public void fill32(){
        int count = 0;
        Integer countId = 1;
        for(int i = 0; i<4;i++){
            for (int k=0; k<8;k++){
                Team home = teamService.getTeamById(k+1+count);
                Team away = teamService.getTeamById(16-k+count);
                Game game = new Game();
                if(i<2){
                    Date date = new Date(122,2,15);
                    game.setDate(date);
                } else {
                    Date date = new Date(122,2,16);
                    game.setDate(date);
                }

                Time time = new Time(12,0,0);
                game.setCompleted(false);
                game.setHome(home);
                game.setAway(away);

                game.setTime(time);
                game.setGameId(countId);
                gameRepo.save(game);
                countId++;

            }
            count += 16;
        }
    }
}
