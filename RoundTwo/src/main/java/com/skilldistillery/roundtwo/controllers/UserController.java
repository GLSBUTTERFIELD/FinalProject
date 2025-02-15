package com.skilldistillery.roundtwo.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.roundtwo.entities.User;
import com.skilldistillery.roundtwo.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin({"*", "http://localhost/"})
@RequestMapping("api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("users/{userId}")
	public User show(@PathVariable("userId") int userId, Principal principal, HttpServletRequest req, HttpServletResponse resp) {
		User foundUser = userService.findUserById(principal.getName(), userId);
		if(foundUser == null) {
			resp.setStatus(404);
		}
		return foundUser;
		
	}

}
