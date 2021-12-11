package MarchMadnessProject.Picks.services;

import MarchMadnessProject.Picks.repos.PlayerRepo;
import org.springframework.stereotype.Service;

@Service
public class PlayerService{
    private PlayerRepo repo;

    public PlayerService(PlayerRepo repo){
        this.repo=repo;
    }

    
}
