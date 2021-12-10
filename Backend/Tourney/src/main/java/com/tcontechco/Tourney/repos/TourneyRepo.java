package com.tcontechco.Tourney.repos;

import com.tcontechco.Tourney.models.Tourney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourneyRepo extends JpaRepository<Tourney, Integer> {
}
