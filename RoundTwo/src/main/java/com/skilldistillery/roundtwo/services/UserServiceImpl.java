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
		User managedUser = userRepo.findById(userId).orElse(null);
		
		if(managedUser == null) {
			return managedUser;
		}

		User userDoingUpdating = userRepo.findByUsername(username);
		
		
		if (managedUser != null && (managedUser.getId() == userDoingUpdating.getId() || userDoingUpdating.getRole().equals(ADMINROLE))) {
		
			managedUser.setUsername(newUserData.getUsername());
			managedUser.setPassword(newUserData.getPassword());
			managedUser.setImageUrl(newUserData.getImageUrl());
			managedUser.setBiography(newUserData.getBiography());
			managedUser.setEmail(newUserData.getEmail());
			managedUser.setEnabled(newUserData.isEnabled());
			userRepo.saveAndFlush(managedUser);
		}
		return managedUser;
	}
	
	@Override
	public boolean destroy(String username, int todoId) {
		boolean deleted = false;
		User deleteUser = userRepo.findByUsernameAndId(username, todoId);
		Optional<User> userOpt = userRepo.findById(todoId);
		User checkUser = userOpt.get();
		if(deleteUser != checkUser) {
			return false;
		}else if (deleteUser == checkUser) {
			userRepo.deleteById(todoId);
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
