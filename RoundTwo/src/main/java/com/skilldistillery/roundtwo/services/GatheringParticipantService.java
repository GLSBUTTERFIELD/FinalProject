package com.skilldistillery.roundtwo.services;

import com.skilldistillery.roundtwo.entities.GatheringParticipant;

public interface GatheringParticipantService {
	
	GatheringParticipant addParticipant(String username, int gatheringId);

}
