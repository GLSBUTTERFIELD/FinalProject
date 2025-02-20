package com.skilldistillery.roundtwo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.roundtwo.entities.Game;
import com.skilldistillery.roundtwo.entities.GameResource;
import com.skilldistillery.roundtwo.entities.User;
import com.skilldistillery.roundtwo.repositories.GameRepository;
import com.skilldistillery.roundtwo.repositories.GameResourceRepository;
import com.skilldistillery.roundtwo.repositories.UserRepository;

@Service
public class GameResourceServiceImpl implements GameResourceService {
	
	@Autowired
	private GameResourceRepository gameResourceRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private GameRepository gameRepo;

	@Override
	public GameResource create(int gameId, GameResource newGameResource, String username) {
		Game game = gameRepo.findById(gameId).orElse(null);
		User user = userRepo.findByUsername(username);
		if (game != null && user != null) {
			newGameResource.setGame(game);
			newGameResource.setUser(user);
			return gameResourceRepo.saveAndFlush(newGameResource);
		}
			
		return null;
	}

}
