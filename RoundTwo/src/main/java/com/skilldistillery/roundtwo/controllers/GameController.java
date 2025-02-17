package com.skilldistillery.roundtwo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.roundtwo.entities.Game;
import com.skilldistillery.roundtwo.services.GameService;

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
	
}
