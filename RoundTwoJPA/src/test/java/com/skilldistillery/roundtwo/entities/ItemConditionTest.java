package com.skilldistillery.roundtwo.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class ItemConditionTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private ItemCondition condition;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("RoundTwoJPA");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		condition = em.find(ItemCondition.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		condition = null;
		em.close();
	}

	@Test
	void test_ItemCondition_mapping() {
		assertNotNull(condition);
//		assertEquals("Excellent Condition", condition.getName());
	}
	
	@Test
	void test_ItemCondition_InventoryItem_OneToMany_mapping() {
		assertNotNull(condition.getInventoryItems());
		assertTrue(condition.getInventoryItems().size()>0);
	}

}
