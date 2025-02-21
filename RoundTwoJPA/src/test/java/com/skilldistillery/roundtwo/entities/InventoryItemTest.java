package com.skilldistillery.roundtwo.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class InventoryItemTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private InventoryItem inventoryItem;

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
		inventoryItem = em.find(InventoryItem.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		inventoryItem = null;
		em.close();
	}

	@Test
	void test_InventoryItem_mapping() {
		assertNotNull(inventoryItem);
//		assertEquals("Twister Delux Edition", inventoryItem.getName());
	}
	
//	@Test
//	void test_InventoryItem_ItemCondition_ManyToOne_mapping() {
//		assertNotNull(inventoryItem.getCondition());
//		assertEquals("Excellent Condition", inventoryItem.getCondition().getName());
//	}
//	
//	@Test
//	void test_InventoryItem_Game_ManyToOne_mapping() {
//		assertNotNull(inventoryItem.getGame());
//		assertEquals("Twister", inventoryItem.getGame().getName());
//	}
//	
//	@Test
//	void test_InventoryItem_User_ManyToOne_mapping() {
//		assertNotNull(inventoryItem.getUser());
//		assertEquals("will", inventoryItem.getUser().getFirstName());
//	}
//	
	@Test
	void test_InventoryItem_InventoryItemComment_OneToMany_mapping() {
		assertNotNull(inventoryItem.getComments());
		assertTrue(inventoryItem.getComments().size() > 0);
	}


}
