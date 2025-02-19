package com.skilldistillery.roundtwo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.roundtwo.entities.ItemCondition;
import com.skilldistillery.roundtwo.repositories.ItemConditionRepository;

@Service
public class ItemConditionServiceImpl implements ItemConditionService {

	@Autowired
	private ItemConditionRepository conditionRepo;
	
	@Override
	public List<ItemCondition> findAllItemConditions() {
		return conditionRepo.findAll();
	}

}
