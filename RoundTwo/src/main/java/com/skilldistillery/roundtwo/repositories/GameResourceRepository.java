package com.skilldistillery.roundtwo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.roundtwo.entities.GameResource;

public interface GameResourceRepository extends JpaRepository<GameResource, Integer>{

}
