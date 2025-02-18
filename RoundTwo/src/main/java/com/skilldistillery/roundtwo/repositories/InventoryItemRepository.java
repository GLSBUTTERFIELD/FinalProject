package com.skilldistillery.roundtwo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.roundtwo.entities.InventoryItem;

public interface InventoryItemRepository extends JpaRepository <InventoryItem, Integer> {
	
	List<InventoryItem> findByAvaliableTrue();
	
}
