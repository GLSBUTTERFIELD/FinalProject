package com.skilldistillery.roundtwo.services;

import java.util.List;

import com.skilldistillery.roundtwo.entities.Game;

public interface GameService {
	List<Game> listAll();
	Game findById(int gameId);
	Game create(Game game);
	Game edit(Game game, int gameId);
	boolean destroy (String username, int gameId);

}
