package com.skilldistillery.roundtwo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.roundtwo.entities.Gathering;
import com.skilldistillery.roundtwo.repositories.GatheringRepository;

@Service
public class GatheringServiceImpl implements GatheringService {

	@Autowired
	private GatheringRepository gatheringRepo;
	
	@Override
	public List<Gathering> index() {
		return gatheringRepo.findAll();
	}

	@Override
	public Gathering findById(int gatheringId) {
		return gatheringRepo.findById(gatheringId).orElse(null);
	}

}
