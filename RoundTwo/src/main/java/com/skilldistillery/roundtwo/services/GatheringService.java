package com.skilldistillery.roundtwo.services;

import java.util.List;

import com.skilldistillery.roundtwo.entities.Gathering;

public interface GatheringService {

	public List<Gathering> index();
	public Gathering findById(int gatheringId);
	public Gathering create(String username, Gathering gathering);
}
