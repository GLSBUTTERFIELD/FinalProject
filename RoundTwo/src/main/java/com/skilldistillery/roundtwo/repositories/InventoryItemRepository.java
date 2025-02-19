package com.skilldistillery.roundtwo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.roundtwo.entities.InventoryItem;
import com.skilldistillery.roundtwo.entities.User;

public interface InventoryItemRepository extends JpaRepository <InventoryItem, Integer> {
	
	List<InventoryItem> findByAvaliableTrue();
	InventoryItem findByUserAndId(User user, int id);
}
