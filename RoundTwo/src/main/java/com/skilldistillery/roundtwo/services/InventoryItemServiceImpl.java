package com.skilldistillery.roundtwo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.roundtwo.entities.InventoryItem;
import com.skilldistillery.roundtwo.entities.User;
import com.skilldistillery.roundtwo.repositories.InventoryItemRepository;

@Service
public class InventoryItemServiceImpl implements InventoryItemService{

	@Autowired
	private InventoryItemRepository inventoryItemRepo;
	
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

	
}
