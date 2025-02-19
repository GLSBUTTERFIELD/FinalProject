package com.skilldistillery.roundtwo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.roundtwo.entities.ItemCondition;
import com.skilldistillery.roundtwo.services.ItemConditionService;

@RestController
@CrossOrigin({"*", "http://localhost/"})
@RequestMapping("api")
public class ItemConditionController {
	
	@Autowired
	private ItemConditionService conditionService;
	
	@GetMapping("ItemConditions")
	public List<ItemCondition> showAvailable() {
		return conditionService.findAllItemConditions();
	}
}
