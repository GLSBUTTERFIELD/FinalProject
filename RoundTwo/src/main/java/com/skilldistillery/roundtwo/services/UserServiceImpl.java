package com.skilldistillery.roundtwo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.roundtwo.entities.User;
import com.skilldistillery.roundtwo.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public User findUserById(String username, int userId) {
		return userRepo.findByUsernameAndId(username, userId);
	}

}
