package com.skilldistillery.roundtwo.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.roundtwo.entities.GatheringParticipant;
import com.skilldistillery.roundtwo.services.GatheringParticipantService;
import com.skilldistillery.roundtwo.services.GatheringService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin({"*", "http://localhost/"})
@RequestMapping("api")
public class GatheringParticipantController {
	
	@Autowired
	private GatheringParticipantService gatheringParticipantService;
	
	@PostMapping("gatherings/{gatheringId}/participants")
	public GatheringParticipant addParticipant (@PathVariable("gatheringId") int gatheringId, HttpServletResponse resp, HttpServletRequest req, Principal principal) {
		GatheringParticipant createdParticipant = null;
		
		try {
			createdParticipant = gatheringParticipantService.addParticipant(principal.getName(), gatheringId);
			if (createdParticipant != null) {
				resp.setStatus(201);
			} else {
				resp.setStatus(404);
			}
				
		} catch (Exception e) {
			resp.setStatus(400);
			e.printStackTrace();
		}
		
		return createdParticipant;
	}
	
	@DeleteMapping("gatherings/{gatheringId}/participants")
	public void deleteParticipant (@PathVariable("gatheringId") int gatheringId, HttpServletResponse resp, HttpServletRequest req, Principal principal) {
		try {
			boolean result = gatheringParticipantService.deleteParticipant(principal.getName(), gatheringId);
			if (result) {
				resp.setStatus(204);
			} else {
				resp.setStatus(404);
			}
		} catch (Exception e) {
			resp.setStatus(400);
			e.printStackTrace();
		}
	}
	

}
