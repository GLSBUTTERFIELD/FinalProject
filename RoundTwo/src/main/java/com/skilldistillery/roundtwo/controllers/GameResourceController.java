package com.skilldistillery.roundtwo.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.roundtwo.entities.GameResource;
import com.skilldistillery.roundtwo.services.GameResourceService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
public class GameResourceController {
	
	@Autowired
	private GameResourceService gameResourceService;
	
	
	@PostMapping("games/{gameId}/resources")
	public GameResource addGameResource(@PathVariable("gameId") int gameId, 
				HttpServletRequest req, HttpServletResponse resp, @RequestBody GameResource gameResource, Principal principal) {
		 try {
			gameResource = gameResourceService.create(gameId, gameResource, principal.getName());
			 if (gameResource == null) {
				 resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			 } else {
				 resp.setStatus(HttpServletResponse.SC_CREATED);
			 }
		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			e.printStackTrace();
			gameResource = null;
		}
		 
		return gameResource;
	}
	

}
