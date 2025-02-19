package com.skilldistillery.roundtwo.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.roundtwo.entities.Gathering;
import com.skilldistillery.roundtwo.services.GatheringService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
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

	// --------------------------------------------------------------------------------------\\
//  GET by Id
	@GetMapping("gatherings/{gatheringId}")
	public Gathering showGathering(@PathVariable("gatheringId") int gatheringId, HttpServletRequest req,
			HttpServletResponse res) {
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
// --------------------------------------------------------------------------------------\\
//GET by HostUsername
	@GetMapping("gatherings/hosted")
	public List<Gathering> findGatheringsHosted(HttpServletResponse res, HttpServletRequest req, Principal principal){
		return gatheringService.findHostedGatherings(principal.getName());
	}
	
//GET 
	@GetMapping("gatherings/attending")
	public List<Gathering> findFutureGatherings(HttpServletResponse res, HttpServletRequest req, Principal principal){
		return gatheringService.findFutureGatherings(principal.getName());
	}
	
//GET 
	@GetMapping("gatherings/attended")
	public List<Gathering> findPastGatherings(HttpServletResponse res, HttpServletRequest req, Principal principal){
		return gatheringService.findPastGatherings(principal.getName());
	}

	
//POST new gathering
	@PostMapping("gatherings")
	public Gathering add(@RequestBody Gathering gathering, HttpServletResponse res, HttpServletRequest req,
			Principal principal) {
		Gathering createdGathering = null;
		try {
			createdGathering = gatheringService.create(principal.getName(), gathering);
			if (createdGathering == null) {
				res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			} else {
				res.setStatus(HttpServletResponse.SC_CREATED);
				res.setHeader("Location", req.getRequestURL().append('/').append(createdGathering.getId()).toString());
			}
		} catch (Exception e) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			createdGathering = null;
			e.printStackTrace();
		}
		return createdGathering;
	}

	// --------------------------------------------------------------------------------------\\

//PUT edit gathering
	@PutMapping("gatherings/{gatheringId}")
	public Gathering edit(@PathVariable("gatheringId") int gatheringId, @RequestBody Gathering gathering,
			HttpServletResponse res, HttpServletRequest req, Principal principal) {
		gathering = gatheringService.edit(principal.getName(), gatheringId, gathering);
		try {
			if (gathering == null) {
				res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		} catch (Exception e) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			gathering = null;
			e.printStackTrace();
		}
		return gathering;
	}

// ------------------------------------------------------------------------------------------\\
// Deleting Gathering
	@DeleteMapping("gatherings/{gatheringId}")
	public void destroy(HttpServletResponse resp, Principal principal, @PathVariable("gatheringId") int gatheringId) {
		try {
			boolean wasdeleted = gatheringService.destroy(principal.getName(), gatheringId);
			if (!wasdeleted) {
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);// 404
			} else {
				resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);// 400
		}
	}

}
