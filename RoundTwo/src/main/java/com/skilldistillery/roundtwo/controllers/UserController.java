package com.skilldistillery.roundtwo.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping ("users")
	public List<User> showAll(Principal principal){
		return userService.findAll(principal.getName());		
	}
	
	// ------------------------------------------------------------------------------------------\\
				//Showing User
	
	@GetMapping("users/{userId}")
	public User show(@PathVariable("userId") int userId, Principal principal, HttpServletRequest req, HttpServletResponse resp) {
		User foundUser = userService.findUserById(principal.getName(), userId);
		if(foundUser == null) {
			resp.setStatus(404);
		}
		return foundUser;
		
	}
	
	// ------------------------------------------------------------------------------------------\\
			//Updating User
	@PutMapping("users/{userId}")
	public User update(HttpServletRequest req, Principal principal, HttpServletResponse resp, @PathVariable(name = "userId") int userId,
			@RequestBody User updateUserData) {
		User updateUser = null;
		try {
			updateUser = userService.update(principal.getName(), userId, updateUserData);
			if (updateUserData == null) {
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);// 404
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);// 400

		}
		resp.setStatus(HttpServletResponse.SC_OK);// 200
		return updateUser;
	}
	
	// ------------------------------------------------------------------------------------------\\
				//Deleting User
	@DeleteMapping("users/{userId}")
	public void destroy(HttpServletResponse resp,Principal principal, @PathVariable(name = "userId") int userId) {
		try {
			boolean wasdeleted = userService.destroy(principal.getName(), userId);
			if (!wasdeleted) {
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);// 404
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);// 400
		}
	}
}
