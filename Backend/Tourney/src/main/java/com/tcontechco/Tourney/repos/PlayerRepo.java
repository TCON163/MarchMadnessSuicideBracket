package com.tcontechco.Tourney.repos;

import com.tcontechco.Tourney.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface PlayerRepo extends JpaRepository<Player,Integer> {
    Optional<Player> findByUsername(String username);
}
