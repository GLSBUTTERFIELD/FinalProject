package com.skilldistillery.roundtwo.services;

import com.skilldistillery.roundtwo.entities.User;

public interface UserService {
	
	public User findUserById(String username, int userId);

}
