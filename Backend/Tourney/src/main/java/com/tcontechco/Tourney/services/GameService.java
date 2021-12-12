package com.tcontechco.Tourney.services;

import com.tcontechco.Tourney.exceptions.ResourceDoesNotExist;
import com.tcontechco.Tourney.models.Game;
import com.tcontechco.Tourney.models.Team;
import com.tcontechco.Tourney.repos.GameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Game> getCompletedGames(){
        return gameRepo.findAll().stream().filter(game -> game.getCompleted().equals(true)).collect(Collectors.toList());
    }

    public List<Game> getGamesNotCompleted(){
        return gameRepo.findAll().stream().filter(game -> game.getCompleted().equals(false)).collect(Collectors.toList());
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



    public void fill33to48(){
        int count = 0;
        Integer countId = 33;
        for(int i = 0; i<4;i++){
            for(int j = 0; j<4;j++){

                Game low = gameRepo.getById(count+j+1);
                Game high = gameRepo.getById(8-j+count);
                Team home = low.getWinner();
                Team away = high.getWinner();

                Game game = new Game();
                if(i<2){
                    Date date = new Date(122,2,17);
                    game.setDate(date);
                } else {
                    Date date = new Date(122,2,18);
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
         count+=8;
        }
    }

    public void simulate1stRound(){
        for(int i=1; i<33;i++){
            Random random = new Random();
            int upperbound=21;
            int randomInt = random.nextInt(upperbound);
            if (randomInt%2==0){

                Game game = gameRepo.getById(i);
                Team winner = game.getHome();
                game.setWinner(winner);
                game.setCompleted(true);

                gameRepo.save(game);

            }else{
                Game game = gameRepo.getById(i);
                Team winner = game.getAway();
                game.setWinner(winner);
                game.setCompleted(true);
                gameRepo.save(game);
            }

        }
    }


    public void simulate2ndRound(){
        for(int i = 33; i<49;i++){
            Random random = new Random();
            int up = 21;
            int rand = random.nextInt(21);
            if(rand%2==0){
                Game game = gameRepo.getById(i);
                Team winner = game.getHome();
                game.setWinner(winner);
                game.setCompleted(true);
                gameRepo.save(game);
            }else{
                Game game = gameRepo.getById(i);
                Team winner = game.getAway();
                game.setWinner(winner);
                game.setCompleted(true);
                gameRepo.save(game);
            }
        }
    }
}
