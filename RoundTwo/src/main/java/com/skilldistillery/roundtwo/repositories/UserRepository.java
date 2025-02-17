package com.skilldistillery.roundtwo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.roundtwo.entities.User;

public interface UserRepository extends JpaRepository <User, Integer> {
	User findByUsername(String username);
	User findByUsernameAndId(String username, int id);

}
