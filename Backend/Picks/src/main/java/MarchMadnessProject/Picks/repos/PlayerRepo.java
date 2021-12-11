package MarchMadnessProject.Picks.repos;

import MarchMadnessProject.Picks.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepo extends JpaRepository<Player,Integer> {
}
