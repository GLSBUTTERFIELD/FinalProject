package com.skilldistillery.roundtwo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.roundtwo.entities.InventoryItemComment;

public interface InventoryItemCommentRepository extends JpaRepository<InventoryItemComment, Integer> {
	InventoryItemComment findByUserUsernameAndId(String username, int id);
	List<InventoryItemComment> findByUserUsernameAndInventoryItemId(String username, int itemId);
}
