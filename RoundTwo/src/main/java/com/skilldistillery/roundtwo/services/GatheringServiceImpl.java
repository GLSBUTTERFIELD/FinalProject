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

}
