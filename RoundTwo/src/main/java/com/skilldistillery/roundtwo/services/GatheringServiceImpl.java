package com.skilldistillery.roundtwo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.roundtwo.entities.Gathering;
import com.skilldistillery.roundtwo.entities.User;
import com.skilldistillery.roundtwo.repositories.GatheringRepository;
import com.skilldistillery.roundtwo.repositories.UserRepository;

@Service
public class GatheringServiceImpl implements GatheringService {

	@Autowired
	private GatheringRepository gatheringRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public List<Gathering> index() {
		return gatheringRepo.findAll();
	}

	@Override
	public Gathering findById(int gatheringId) {
		return gatheringRepo.findById(gatheringId).orElse(null);
	}

	@Override
	public Gathering create(String username, Gathering gathering) {
		User managedUser = userRepo.findByUsername(username);
		if (managedUser == null) {
			return null;			
		}
		gathering.setHost(managedUser);
		gathering.setEnabled(true);
		return gatheringRepo.saveAndFlush(gathering);
	}

	@Override
	public Gathering edit(String username, int gatheringId, Gathering gathering) {
		Gathering managedGathering = gatheringRepo.findByHostUsernameAndId(username, gatheringId);
		if (managedGathering != null) {
			managedGathering.setAddress(gathering.getAddress());
			managedGathering.setDescription(gathering.getDescription());
			managedGathering.setStartDate(gathering.getStartDate());
			managedGathering.setEndDate(gathering.getEndDate());
			managedGathering.setStartTime(gathering.getStartTime());
			managedGathering.setEndTime(gathering.getEndTime());
			managedGathering.setFee(gathering.getFee());
			managedGathering.setGames(gathering.getGames());
			managedGathering.setImageUrl(gathering.getImageUrl());
			managedGathering.setMaxParticipants(gathering.getMaxParticipants());
			managedGathering.setMinParticipants(gathering.getMinParticipants());
			managedGathering.setName(gathering.getName());
			gatheringRepo.saveAndFlush(managedGathering);
		}
		return managedGathering;
	}

	@Override
	public boolean destroy(String username, int gatheringId) {
		boolean deleted = false;
		//TODO: IMPORTAINT FOR DELETE FUNCTIONALITY
		//need to verify User (host) who created the 
		//gathering is the only one who can delete it
		return deleted;
	}

}
