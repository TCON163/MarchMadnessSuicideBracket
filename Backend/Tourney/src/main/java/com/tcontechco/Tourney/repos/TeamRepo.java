package com.tcontechco.Tourney.repos;

import com.tcontechco.Tourney.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepo extends JpaRepository<Team, Integer> {
}
