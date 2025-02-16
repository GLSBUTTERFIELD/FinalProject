package com.skilldistillery.roundtwo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.roundtwo.entities.Gathering;
import com.skilldistillery.roundtwo.services.GatheringService;

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
}
