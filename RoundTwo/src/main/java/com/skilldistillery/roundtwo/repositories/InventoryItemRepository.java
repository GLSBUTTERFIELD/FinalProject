package com.skilldistillery.roundtwo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.roundtwo.entities.InventoryItem;

public interface InventoryItemRepository extends JpaRepository <InventoryItem, Integer> {
	
	

}
