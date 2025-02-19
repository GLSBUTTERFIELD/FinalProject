package com.skilldistillery.roundtwo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.roundtwo.entities.Game;
import com.skilldistillery.roundtwo.entities.InventoryItem;
import com.skilldistillery.roundtwo.entities.User;
import com.skilldistillery.roundtwo.repositories.GameRepository;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	private GameRepository gameRepo;

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

}
