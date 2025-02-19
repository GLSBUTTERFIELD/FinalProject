package com.skilldistillery.roundtwo.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.roundtwo.entities.Address;
import com.skilldistillery.roundtwo.entities.Game;
import com.skilldistillery.roundtwo.entities.User;
import com.skilldistillery.roundtwo.services.GameService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin({"*", "http://localhost/"})
@RequestMapping("api")
public class GameController {
@Autowired
private GameService gameService;
	
	@GetMapping ("games")
	public List<Game> showAll(){
		return gameService.listAll();		
	}
	
	@PostMapping("games")
	public Game create(@RequestBody Game game, HttpServletResponse res, HttpServletRequest req) {
			Game createdGame = null;
			try {
				createdGame = gameService.create(game);
				if (createdGame == null) {
					res.setStatus(HttpServletResponse.SC_NOT_FOUND);
				} else {
					res.setStatus(HttpServletResponse.SC_CREATED);
					res.setHeader("Location", req.getRequestURL().append('/').append(createdGame.getId()).toString());
				}
			} catch (Exception e) {
				res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				createdGame = null;
				e.printStackTrace();
			}
			return createdGame;
		}
	@PutMapping("games/{gameId}")
	public Game edit(@PathVariable("gameId") int gameId, @RequestBody Game game, HttpServletRequest req, HttpServletResponse res) {
		game = gameService.edit(game, gameId);
		try {
			if (game == null) {
				res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		} catch (Exception e) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			game = null;
			e.printStackTrace();
		}
		return game;
	}
	
}
