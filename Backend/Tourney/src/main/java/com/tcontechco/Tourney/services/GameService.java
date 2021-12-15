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

    public List<Game> getListOfGamesByDate(Date date){
        return gameRepo.getListOfGamesByDate(date);
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
                    Date date = new Date(122,2,19);
                    game.setDate(date);
                } else {
                    Date date = new Date(122,2,20);
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

    public void fillSweet16(){
        int count = 33;
        Integer countId = 49;
        for(int i = 0; i<8; i++){
            Game low = gameRepo.getById(count);
            Game high = gameRepo.getById(count+1);
            Team home = low.getWinner();
            Team away = high.getWinner();

            Game game = new Game();
            if(i<4){
                Date date = new Date(122,2,24);
                game.setDate(date);
            } else {
                Date date = new Date(122,2,25);
                game.setDate(date);
            }

            Time time = new Time(12,0,0);
            game.setCompleted(false);
            game.setHome(home);
            game.setAway(away);

            game.setTime(time);
            game.setGameId(countId);
            gameRepo.save(game);

            count+=2;
            countId++;
        }

    }


    public void fillElite8(){
        int count = 49;
        Integer countId = 57;
        for(int i = 0; i<4;  i++){
            Game low = gameRepo.getById(count);
            Game high = gameRepo.getById(count+1);
            Team home = low.getWinner();
            Team away = high.getWinner();

            Game game = new Game();
            if(i<2){
                Date date = new Date(122,2,26);
                game.setDate(date);
            } else {
                Date date = new Date(122,2,27);
                game.setDate(date);
            }

            Time time = new Time(12,0,0);
            game.setCompleted(false);
            game.setHome(home);
            game.setAway(away);

            game.setTime(time);
            game.setGameId(countId);
            gameRepo.save(game);

            count+=2;
            countId++;
        }
    }

    public void fillFinalFour(){
        Game fiftyseven = gameRepo.getById(57);
        Game fiftyeight = gameRepo.getById(58);
        Team west = fiftyseven.getWinner();
        Team east = fiftyeight.getWinner();
        Game sixty1 = new Game();
        Date date = new Date(122,3,2);
        Time time = new Time(12,0,0);
        sixty1.setCompleted(false);
        sixty1.setHome(west);
        sixty1.setAway(east);
        sixty1.setTime(time);
        sixty1.setDate(date);
        sixty1.setGameId(61);
        gameRepo.save(sixty1);

        Game fifty9 = gameRepo.getById(59);
        Game sixty = gameRepo.getById(60);
        Team south = fifty9.getWinner();
        Team midWest = sixty.getWinner();
        Game sixty2 = new Game();
        sixty2.setCompleted(false);
        sixty2.setHome(south);
        sixty2.setAway(midWest);
        sixty2.setTime(time);
        sixty2.setDate(date);
        sixty2.setGameId(62);
        gameRepo.save(sixty2);
    }

    public void fillChamp(){
        Game west = gameRepo.getById(61);
        Game east = gameRepo.getById(62);
        Team home = west.getWinner();
        Team away = east.getWinner();
        Game game = new Game();
        Date date = new Date(122,3,4);
        Time time = new Time(12,0,0);
        game.setCompleted(false);
        game.setHome(home);
        game.setAway(away);
        game.setDate(date);
        game.setTime(time);
        game.setGameId(63);
        gameRepo.save(game);
    }

    public void simulate1stRound(){
        for(int i=1; i<33;i++){
            Random random = new Random();
            int upperbound=21;
            int randomInt = random.nextInt(upperbound);
            if (randomInt%2==0){
                Game game = gameRepo.getById(i);
                Team winner = game.getHome();
                Team loser = game.getAway();
                loser.setAlive(false);
                teamService.createTeam(loser);
                game.setWinner(winner);
                game.setCompleted(true);
                gameRepo.save(game);

            }else{
                Game game = gameRepo.getById(i);
                Team winner = game.getAway();
                Team loser = game.getHome();
                loser.setAlive(false);
                teamService.createTeam(loser);
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
                Team loser = game.getAway();
                loser.setAlive(false);
                teamService.createTeam(loser);
                game.setWinner(winner);
                game.setCompleted(true);
                gameRepo.save(game);
            }else{
                Game game = gameRepo.getById(i);
                Team winner = game.getAway();
                Team loser = game.getHome();
                loser.setAlive(false);
                teamService.createTeam(loser);
                game.setWinner(winner);
                game.setCompleted(true);
                gameRepo.save(game);
            }
        }
    }


    public void simulateSweet16(){
        for(int i = 49; i<57; i++){
            Random random = new Random();
            int up = 21;
            int rand = random.nextInt(21);
            if(rand%2==0){
                Game game = gameRepo.getById(i);
                Team winner = game.getHome();
                Team loser = game.getAway();
                loser.setAlive(false);
                teamService.createTeam(loser);
                game.setWinner(winner);
                game.setCompleted(true);
                gameRepo.save(game);
            }else{
                Game game = gameRepo.getById(i);
                Team winner = game.getAway();
                Team loser = game.getHome();
                loser.setAlive(false);
                teamService.createTeam(loser);
                game.setWinner(winner);
                game.setCompleted(true);
                gameRepo.save(game);
            }
        }
    }

    public void simulateElite8(){
        for(int i = 57; i<61;i++){
            Random random = new Random();
            int up = 21;
            int rand = random.nextInt(21);
            if(rand%2==0){
                Game game = gameRepo.getById(i);
                Team winner = game.getHome();
                Team loser = game.getAway();
                loser.setAlive(false);
                teamService.createTeam(loser);
                game.setWinner(winner);
                game.setCompleted(true);
                gameRepo.save(game);
            }else{
                Game game = gameRepo.getById(i);
                Team winner = game.getAway();
                Team loser = game.getHome();
                loser.setAlive(false);
                teamService.createTeam(loser);
                game.setWinner(winner);
                game.setCompleted(true);
                gameRepo.save(game);
            }
        }
    }



    public void simulateFinalFour(){
        Random random = new Random();
        int rand = random.nextInt(21);
        if(rand%2==0){
            Game game = gameRepo.getById(61);
            Team winner = game.getHome();
            Team loser = game.getAway();
            loser.setAlive(false);
            teamService.createTeam(loser);
            game.setWinner(winner);
            game.setCompleted(true);
            gameRepo.save(game);
        }else{
            Game game = gameRepo.getById(61);
            Team winner = game.getAway();
            Team loser = game.getHome();
            loser.setAlive(false);
            teamService.createTeam(loser);
            game.setWinner(winner);
            game.setCompleted(true);
            gameRepo.save(game);
        }

        Random random2 = new Random();
        int rand2 = random2.nextInt(21);
        if(rand2%2==0){
            Game game = gameRepo.getById(62);
            Team winner = game.getHome();
            Team loser = game.getAway();
            loser.setAlive(false);
            teamService.createTeam(loser);
            game.setWinner(winner);
            game.setCompleted(true);
            gameRepo.save(game);
        }else{
            Game game = gameRepo.getById(62);
            Team winner = game.getAway();
            Team loser = game.getHome();
            loser.setAlive(false);
            teamService.createTeam(loser);
            game.setWinner(winner);
            game.setCompleted(true);
            gameRepo.save(game);
        }

    }

    public void simulateChamp(){
        Random random = new Random();
        int rand = random.nextInt(21);
        if(rand>9){
            Game game = gameRepo.getById(63);
            Team winner = game.getHome();
            Team loser = game.getAway();
            loser.setAlive(false);
            teamService.createTeam(loser);
            game.setWinner(winner);
            game.setCompleted(true);
            gameRepo.save(game);
        }else{
            Game game = gameRepo.getById(63);
            Team winner = game.getAway();
            Team loser = game.getHome();
            loser.setAlive(false);
            teamService.createTeam(loser);
            game.setWinner(winner);
            game.setCompleted(true);
            gameRepo.save(game);
        }
    }

}
