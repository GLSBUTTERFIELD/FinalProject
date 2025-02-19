package com.skilldistillery.roundtwo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.roundtwo.entities.ItemCondition;

public interface ItemConditionRepository extends JpaRepository<ItemCondition, Integer> {

}
