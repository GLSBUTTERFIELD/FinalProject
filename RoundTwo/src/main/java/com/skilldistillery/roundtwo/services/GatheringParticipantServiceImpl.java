package com.skilldistillery.roundtwo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.roundtwo.entities.Gathering;
import com.skilldistillery.roundtwo.entities.GatheringParticipant;
import com.skilldistillery.roundtwo.entities.GatheringParticipantId;
import com.skilldistillery.roundtwo.entities.User;
import com.skilldistillery.roundtwo.repositories.GatheringParticipantRepository;
import com.skilldistillery.roundtwo.repositories.GatheringRepository;
import com.skilldistillery.roundtwo.repositories.UserRepository;

@Service
public class GatheringParticipantServiceImpl implements GatheringParticipantService {

	@Autowired
	private GatheringParticipantRepository gatheringParticipantRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private GatheringRepository gatheringRepo;

	@Override
	public GatheringParticipant addParticipant(String username, int gatheringId) {
		User participant = userRepo.findByUsername(username);
		Gathering gathering = gatheringRepo.findById(gatheringId).orElse(null);
		GatheringParticipant newParticipant = null; 
		if(participant != null && gathering != null) {
			GatheringParticipantId id = new GatheringParticipantId(gatheringId, participant.getId());
			newParticipant = new GatheringParticipant();
			newParticipant.setId(id);
			newParticipant.setUser(participant);
			newParticipant.setGathering(gathering);
			gatheringParticipantRepo.saveAndFlush(newParticipant);
		}
		
		return newParticipant;
	}
	
	
	
}
