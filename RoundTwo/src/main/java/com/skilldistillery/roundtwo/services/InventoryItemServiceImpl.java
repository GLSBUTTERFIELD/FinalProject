package com.skilldistillery.roundtwo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.roundtwo.entities.InventoryItem;
import com.skilldistillery.roundtwo.entities.User;
import com.skilldistillery.roundtwo.repositories.InventoryItemRepository;
import com.skilldistillery.roundtwo.repositories.UserRepository;

@Service
public class InventoryItemServiceImpl implements InventoryItemService{

	@Autowired
	private InventoryItemRepository inventoryItemRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public List<InventoryItem> showAvailable() {
		return inventoryItemRepo.findByAvailableTrue();
	}

	@Override
	public InventoryItem findById(int itemId) {
		return inventoryItemRepo.findById(itemId).orElse(null);
	}

	@Override
	public InventoryItem edit(User user, int itemId, InventoryItem inventoryItem) {
		InventoryItem managedInventoryItem = inventoryItemRepo.findByUserAndId(user, itemId);
		if (managedInventoryItem != null) {
			managedInventoryItem.setName(inventoryItem.getName());
			managedInventoryItem.setNotes(inventoryItem.getNotes());
			managedInventoryItem.setImageUrl(inventoryItem.getImageUrl());
			managedInventoryItem.setAvailable(inventoryItem.isAvailable());
			managedInventoryItem.setCondition(inventoryItem.getCondition());
			inventoryItemRepo.saveAndFlush(managedInventoryItem);
		}
		return managedInventoryItem;
	}

	@Override
	public InventoryItem create(String username, InventoryItem inventoryItem) {
		User managedUser = userRepo.findByUsername(username);
		if (managedUser == null) {
			return null;
		}
		inventoryItem.setUser(managedUser);
		return inventoryItemRepo.saveAndFlush(inventoryItem);
	}
	


}
