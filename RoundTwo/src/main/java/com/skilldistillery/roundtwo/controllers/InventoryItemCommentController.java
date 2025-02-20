package com.skilldistillery.roundtwo.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.roundtwo.entities.InventoryItem;
import com.skilldistillery.roundtwo.entities.InventoryItemComment;
import com.skilldistillery.roundtwo.services.InventoryItemCommentService;
import com.skilldistillery.roundtwo.services.InventoryItemService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
public class InventoryItemCommentController {

	@Autowired
	private InventoryItemCommentService commentService;

	@Autowired
	private InventoryItemService inventoryService;

	@GetMapping("inventoryItems/comments")
	public List<InventoryItemComment> listAll() {
		return commentService.findAll();
	}

	@GetMapping("inventoryItems/comments/{commentId}")
	public InventoryItemComment showComment(@PathVariable("commentId") int commentId, HttpServletRequest req,
			HttpServletResponse res, Principal principal) {
		InventoryItemComment comment = null;
		try {
			comment = commentService.findById(principal.getName(), commentId);
			if (comment == null) {
				res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		} catch (Exception e) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			comment = null;
			e.printStackTrace();
		}
		return comment;
	}

//Get mapping comments for specific inventoryItem
	@GetMapping("inventoryItems/{itemId}/comments")
	public List<InventoryItemComment> showItemComments(@PathVariable("itemId") int itemId, HttpServletRequest req,
			HttpServletResponse res, Principal principal) {
		List<InventoryItemComment> foundComments = commentService.findAllByInventoryItem(principal.getName(), itemId);
		try {
			if (foundComments == null) {
				res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		} catch (Exception e) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			foundComments = null;
			e.printStackTrace();
		}
		return foundComments;
	}

//Post mapping to add a comment to inventoryItem
	@PostMapping("inventoryItems/{itemId}/comments")
	public InventoryItemComment addComment(@PathVariable("itemId") int itemId,
			@RequestBody InventoryItemComment newComment, Principal principal, HttpServletRequest req,
			HttpServletResponse res) {
		try {
			newComment = commentService.addComment(principal.getName(), itemId, newComment);
			if (newComment == null) {
				res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			} else {
				res.setStatus(HttpServletResponse.SC_CREATED);
				res.setHeader("Location", req.getRequestURL().append('/').append(newComment.getId()).toString());
			}
		} catch (Exception e) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			newComment = null;
			e.printStackTrace();
		}
		return newComment;
	}

}
