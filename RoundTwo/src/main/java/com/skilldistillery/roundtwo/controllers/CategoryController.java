package com.skilldistillery.roundtwo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.roundtwo.entities.Category;
import com.skilldistillery.roundtwo.services.CategoryService;

@RestController
@CrossOrigin({"*", "http://localhost/"})
@RequestMapping("api")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("categories")
	public List<Category> showAll() {
		return categoryService.listAll();
	}
}
