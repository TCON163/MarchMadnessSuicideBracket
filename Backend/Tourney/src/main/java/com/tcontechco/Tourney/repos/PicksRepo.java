package com.tcontechco.Tourney.repos;

import com.tcontechco.Tourney.models.Picks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PicksRepo extends JpaRepository<Picks, Integer> {
}
