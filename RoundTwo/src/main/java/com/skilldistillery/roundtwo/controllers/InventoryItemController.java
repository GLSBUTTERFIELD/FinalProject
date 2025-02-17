package com.skilldistillery.roundtwo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.roundtwo.entities.InventoryItem;
import com.skilldistillery.roundtwo.services.InventoryItemService;

@RestController
@CrossOrigin({"*", "http://localhost/"})
@RequestMapping("api")
public class InventoryItemController {
	
	@Autowired
	private InventoryItemService inventoryItemService;
	
	@GetMapping("inventoryItems")
	public List<InventoryItem> showAvailable() {
		return inventoryItemService.showAvailable();
	}

}
