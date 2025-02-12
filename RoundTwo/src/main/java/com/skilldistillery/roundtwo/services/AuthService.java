package com.skilldistillery.roundtwo.services;

import com.skilldistillery.roundtwo.entities.User;

public interface AuthService {
	public User register(User user);
	public User getUserByUsername(String username);
}
