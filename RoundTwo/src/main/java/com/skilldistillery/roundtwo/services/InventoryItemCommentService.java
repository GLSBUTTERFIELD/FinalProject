package com.skilldistillery.roundtwo.services;

import java.util.List;

import com.skilldistillery.roundtwo.entities.InventoryItemComment;

public interface InventoryItemCommentService {
	List<InventoryItemComment> findAll();
	List<InventoryItemComment> findAllByInventoryItem(String username, int itemId);
	InventoryItemComment findById(String username, int commentId);
	InventoryItemComment addComment(String username, int inventoryItemId, InventoryItemComment comment);
}
