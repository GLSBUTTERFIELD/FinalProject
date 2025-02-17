package com.skilldistillery.roundtwo.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.roundtwo.entities.Gathering;
import com.skilldistillery.roundtwo.services.GatheringService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin({"*", "http://localhost/"})
@RequestMapping("api")
public class GatheringController {

	@Autowired
	private GatheringService gatheringService;
	
	
	// --------------------------------------------------------------------------------------\\

//  GET ALL
	@GetMapping("gatherings")
	public List<Gathering> index() {
		return gatheringService.index(); 
	}
	
//  GET by Id
	@GetMapping("gatherings/{gatheringId}")
	public Gathering showGathering (@PathVariable("gatheringId") int gatheringId, HttpServletRequest req, HttpServletResponse res) {
		Gathering foundGathering = null;
		try {
			foundGathering = gatheringService.findById(gatheringId);
			if (foundGathering == null) {
				res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		} catch (Exception e) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			foundGathering = null;
			e.printStackTrace();
		}
		return foundGathering;
	}
	
	
}
