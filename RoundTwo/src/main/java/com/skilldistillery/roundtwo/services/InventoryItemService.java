package com.skilldistillery.roundtwo.services;

import java.util.List;

import com.skilldistillery.roundtwo.entities.InventoryItem;
import com.skilldistillery.roundtwo.entities.User;

public interface InventoryItemService {
	
	public List<InventoryItem> showAvailable();
	public InventoryItem findById(int itemId);
	public InventoryItem edit(String username, int itemId, InventoryItem inventoryItem);
	public InventoryItem create(String username, InventoryItem inventoryItem);
	public boolean disable(String username, int itemId);
}
