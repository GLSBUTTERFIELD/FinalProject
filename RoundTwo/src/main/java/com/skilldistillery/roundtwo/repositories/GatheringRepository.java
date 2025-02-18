package com.skilldistillery.roundtwo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.roundtwo.entities.Gathering;

public interface GatheringRepository extends JpaRepository<Gathering, Integer>{
	Gathering findByHostUsernameAndId (String username, int gatheringId);
	List<Gathering>findByEnabledTrue();
}
