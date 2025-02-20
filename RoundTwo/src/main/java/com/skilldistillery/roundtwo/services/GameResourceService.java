package com.skilldistillery.roundtwo.services;

import com.skilldistillery.roundtwo.entities.GameResource;

public interface GameResourceService {
	
	public GameResource create(int gameId, GameResource newGameResource, String username); 

}
