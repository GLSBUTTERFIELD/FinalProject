package com.skilldistillery.roundtwo.services;

import java.util.List;

import com.skilldistillery.roundtwo.entities.Gathering;

public interface GatheringService {

	public List<Gathering> index();
	public List<Gathering> findHostedGatherings(String username);
	public List<Gathering> findFutureGatherings(String username);
	public List<Gathering> findPastGatherings(String username);
	public Gathering findById(int gatheringId);
	public Gathering create(String username, Gathering gathering);
	public Gathering edit(String username, int gatheringId, Gathering gathering);
	public boolean destroy(String username, int gatheringId);
}
