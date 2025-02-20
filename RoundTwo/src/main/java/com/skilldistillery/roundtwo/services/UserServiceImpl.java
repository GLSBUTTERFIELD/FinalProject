package com.skilldistillery.roundtwo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.roundtwo.entities.User;
import com.skilldistillery.roundtwo.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	private static final String ADMINROLE = "chadmin";

	
	@Override
	public User findUserById(String username, int userId) {
		return userRepo.findByUsernameAndId(username, userId);
	}

	@Override
	public User update(String username, int userId, User newUserData) {
		User updatingUser = userRepo.findByUsernameAndId(username, userId);
		if(updatingUser == null) {
			return updatingUser;
		}
		if(updatingUser.getId() == newUserData.getId()) {
			updatingUser.setUsername(newUserData.getUsername());
			updatingUser.setPassword(newUserData.getPassword());
			updatingUser.setImageUrl(newUserData.getImageUrl());
			updatingUser.setBiography(newUserData.getBiography());
			updatingUser.setEmail(newUserData.getEmail());
			userRepo.saveAndFlush(updatingUser);
		}
		return updatingUser;
	}
	
	@Override
	public boolean destroy(String username, int todoId) {
		boolean deleted = false;
		User deleteUser = userRepo.findByUsername(username);
		User managedUser = userRepo.findById(todoId).orElse(null);
		
		if (managedUser != null && (managedUser.getId() == deleteUser.getId() || deleteUser.getRole().equals(ADMINROLE))) {
			managedUser.setEnabled(false);
			userRepo.saveAndFlush(managedUser);
			deleted = true;
		}
		return deleted;
	}

	@Override
	public User findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public List<User> findAll(String username) {
		User checkAdmin = userRepo.findByUsername(username);
		User admin = userRepo.findById(1).orElse(null);
		if(checkAdmin.getRole() == admin.getRole()) {
			return userRepo.findAll();
	}
		return null;
}

}
