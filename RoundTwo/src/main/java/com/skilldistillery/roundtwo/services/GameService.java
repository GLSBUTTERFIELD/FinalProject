package com.skilldistillery.roundtwo.services;

import java.util.List;

import com.skilldistillery.roundtwo.entities.Game;

public interface GameService {
	List<Game> listAll();
	Game findById(int gameId);

}
