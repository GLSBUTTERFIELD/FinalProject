package com.skilldistillery.roundtwo.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.roundtwo.entities.InventoryItem;
import com.skilldistillery.roundtwo.entities.User;
import com.skilldistillery.roundtwo.services.InventoryItemService;
import com.skilldistillery.roundtwo.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin({"*", "http://localhost/"})
@RequestMapping("api")
public class InventoryItemController {
	
	@Autowired
	private InventoryItemService inventoryItemService;
	
	@Autowired
	private UserService userService;
	
	// --------------------------------------------------------------------------------------\\
		//GET All
	@GetMapping("inventoryItems")
	public List<InventoryItem> showAvailable() {
		return inventoryItemService.showAvailable();
	}

	// --------------------------------------------------------------------------------------\\
	//GET ONE
	@GetMapping("inventoryItems/{itemId}")
	public InventoryItem showItem (@PathVariable("itemId") int itemId, HttpServletRequest req, HttpServletResponse res) {
		InventoryItem foundItem = null;
		try {
			foundItem = inventoryItemService.findById(itemId);
			if (foundItem == null) {
				res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		} catch (Exception e) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			foundItem = null;
			e.printStackTrace();
		}
		return foundItem;
	}
	// --------------------------------------------------------------------------------------\\
		//PUT new edits
	
	@PutMapping("inventoryItems/{itemId}")
	public InventoryItem edit(@PathVariable("itemId") int itemId, @RequestBody InventoryItem inventoryItem,
			HttpServletResponse res, HttpServletRequest req, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		inventoryItem = inventoryItemService.edit(user, itemId, inventoryItem);
		try {
			if (inventoryItem == null) {
				res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		} catch (Exception e) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			inventoryItem = null;
			e.printStackTrace();
		}
		return inventoryItem;
	}
	// --------------------------------------------------------------------------------------\\
		
}
