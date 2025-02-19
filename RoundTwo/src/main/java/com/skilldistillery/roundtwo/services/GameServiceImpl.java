package com.skilldistillery.roundtwo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.roundtwo.entities.Game;
import com.skilldistillery.roundtwo.entities.User;
import com.skilldistillery.roundtwo.repositories.GameRepository;
import com.skilldistillery.roundtwo.repositories.UserRepository;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	private GameRepository gameRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Game> listAll() {
		return gameRepo.findAll();
	}

	@Override
	public Game findById(int gameId) {
		return gameRepo.findById(gameId).orElse(null);
	}

	@Override
	public Game create(Game game) {
		return gameRepo.saveAndFlush(game);
	}

	@Override
	public Game edit(Game game, int gameId) {
		Game managedGame = gameRepo.findById(gameId).orElse(null);
		if (managedGame != null) {
			managedGame.setName(game.getName());
			managedGame.setDescription(game.getDescription());
			managedGame.setMinimumAge(game.getMinimumAge());
			managedGame.setMaxPlayers(game.getMaxPlayers());
			managedGame.setImageUrl(game.getImageUrl());
			managedGame.setWebsiteUrl(game.getWebsiteUrl());
			managedGame.setMinPlayers(game.getMinPlayers());
			gameRepo.saveAndFlush(managedGame);
		}
		return managedGame;
	}

	@Override
	public boolean destroy(String username, int gameId) {
		boolean deleted = false;
		Game managedAdminGame  = gameRepo.findById(gameId).orElse(null);
		User checkAdmin = userRepo.findByUsername(username);
		User admin = userRepo.findById(1).orElse(null);
		if(checkAdmin.getRole() == admin.getRole()) {
			if (managedAdminGame != null) {
				gameRepo.deleteById(gameId);
				deleted = true;
			}
		}
		return deleted;
	}

	
}
