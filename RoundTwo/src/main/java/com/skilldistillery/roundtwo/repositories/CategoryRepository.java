package com.skilldistillery.roundtwo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.roundtwo.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
