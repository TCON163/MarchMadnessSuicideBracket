package MarchMadnessProject.Picks.repos;

import MarchMadnessProject.Picks.models.Games;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamesRepo extends JpaRepository<Games,Integer> {
}
