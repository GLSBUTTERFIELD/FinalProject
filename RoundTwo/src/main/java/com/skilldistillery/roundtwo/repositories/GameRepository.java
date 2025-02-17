package com.skilldistillery.roundtwo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.roundtwo.entities.Game;

public interface GameRepository extends JpaRepository<Game, Integer>{

}
