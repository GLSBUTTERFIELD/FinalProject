package com.skilldistillery.roundtwo.services;

import com.skilldistillery.roundtwo.entities.User;

public interface UserService {
	
	public User findUserById(String username, int userId);
	
	public User findByUsername(String username);
	
	public User update(String username, int userId, User newUserData);
	
	public boolean destroy(String username, int userId);
}
