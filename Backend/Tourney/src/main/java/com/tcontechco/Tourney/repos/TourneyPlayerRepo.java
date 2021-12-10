package com.tcontechco.Tourney.repos;

import com.tcontechco.Tourney.models.TourneyPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourneyPlayerRepo extends JpaRepository<TourneyPlayer, Integer> {
}
