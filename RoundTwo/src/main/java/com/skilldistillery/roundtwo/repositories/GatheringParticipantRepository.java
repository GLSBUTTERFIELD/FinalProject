package com.skilldistillery.roundtwo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.roundtwo.entities.GatheringParticipant;
import com.skilldistillery.roundtwo.entities.GatheringParticipantId;

public interface GatheringParticipantRepository extends JpaRepository<GatheringParticipant, GatheringParticipantId> {
	

}
