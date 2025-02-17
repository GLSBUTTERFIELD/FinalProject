package com.skilldistillery.roundtwo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.roundtwo.entities.InventoryItem;
import com.skilldistillery.roundtwo.repositories.InventoryItemRepository;

@Service
public class InventoryItemServiceImpl implements InventoryItemService{

	@Autowired
	private InventoryItemRepository inventoryItemRepo;
	
	@Override
	public List<InventoryItem> showAvailable() {
		return inventoryItemRepo.findByAvaliableTrue();
	}

	@Override
	public InventoryItem findById(int itemId) {
		return inventoryItemRepo.findById(itemId).orElse(null);
	}
	

	
}
