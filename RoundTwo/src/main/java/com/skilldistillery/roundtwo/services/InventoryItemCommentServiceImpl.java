package com.skilldistillery.roundtwo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.roundtwo.entities.InventoryItem;
import com.skilldistillery.roundtwo.entities.InventoryItemComment;
import com.skilldistillery.roundtwo.entities.User;
import com.skilldistillery.roundtwo.repositories.InventoryItemCommentRepository;
import com.skilldistillery.roundtwo.repositories.InventoryItemRepository;
import com.skilldistillery.roundtwo.repositories.UserRepository;
@Service
public class InventoryItemCommentServiceImpl implements InventoryItemCommentService {

	@Autowired
	private InventoryItemCommentRepository commentRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private InventoryItemRepository itemRepo;
	
	@Override
	public List<InventoryItemComment> findAll() {
		return commentRepo.findAll();
	}

	@Override
	public InventoryItemComment findById(String username, int commentId) {
		return commentRepo.findByUserUsernameAndId(username, commentId);
	}

	@Override
	public List<InventoryItemComment> findAllByInventoryItem(String username, int itemId) {
		return commentRepo.findByUserUsernameAndInventoryItemId(username, itemId);
	}
	
	@Override
	public InventoryItemComment addComment(String username, int inventoryItemId, InventoryItemComment comment) {
		User managedUser = userRepo.findByUsername(username);
		InventoryItem managedItem = itemRepo.findById(inventoryItemId).orElse(null);
		InventoryItemComment newComment = new InventoryItemComment ();
		if (managedUser == null) {
			return null;
		}
		newComment.setComment(comment.getComment());
		newComment.setParentComment(comment.getParentComment());
		newComment.setUser(managedUser);
		newComment.setEnabled(true);
		managedItem.getComments().add(newComment);
		commentRepo.saveAndFlush(newComment);
		itemRepo.saveAndFlush(managedItem);
		return newComment;
	}

	

}
