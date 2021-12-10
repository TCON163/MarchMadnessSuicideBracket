package com.tcontechco.User.repos;

import com.tcontechco.User.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Integer> {
}
