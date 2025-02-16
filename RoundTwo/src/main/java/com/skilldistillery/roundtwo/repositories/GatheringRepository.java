package com.skilldistillery.roundtwo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.roundtwo.entities.Gathering;

public interface GatheringRepository extends JpaRepository<Gathering, Integer>{

}
