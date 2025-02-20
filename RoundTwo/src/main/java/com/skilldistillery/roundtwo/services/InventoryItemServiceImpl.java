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
	
	private static final String ADMINROLE = "chadmin";

	
	@Override
	public List<InventoryItem> showAvailable() {
		return inventoryItemRepo.findByAvailableTrue();
	}

	@Override
	public InventoryItem findById(int itemId) {
		return inventoryItemRepo.findById(itemId).orElse(null);
	}

	@Override
	public InventoryItem edit(String username, int itemId, InventoryItem inventoryItem) {
		InventoryItem managedInventoryItem = inventoryItemRepo.findByUser_UsernameAndId(username, itemId);
		if (managedInventoryItem != null) {
			managedInventoryItem.setName(inventoryItem.getName());
			managedInventoryItem.setNotes(inventoryItem.getNotes());
			managedInventoryItem.setImageUrl(inventoryItem.getImageUrl());
			managedInventoryItem.setAvailable(inventoryItem.isAvailable());
			managedInventoryItem.setCondition(inventoryItem.getCondition());
			return inventoryItemRepo.saveAndFlush(managedInventoryItem);
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

	@Override
	public boolean disable(String username, int itemId) {
		boolean deleted = false;
		InventoryItem managedItem = inventoryItemRepo.findByUser_UsernameAndId(username, itemId);
		User managedUser = userRepo.findByUsername(username);
		if (managedItem != null && (managedItem.getUser().getId() == managedUser.getId() || managedUser.getRole().equals(ADMINROLE))) {
			managedItem.setEnabled(false);
			inventoryItemRepo.saveAndFlush(managedItem);
			deleted = true;
		}
		return deleted;
	}
	

}
