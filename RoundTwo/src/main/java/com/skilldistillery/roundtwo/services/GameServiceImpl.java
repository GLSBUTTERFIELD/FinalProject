package com.skilldistillery.roundtwo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.roundtwo.entities.Game;
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

}
