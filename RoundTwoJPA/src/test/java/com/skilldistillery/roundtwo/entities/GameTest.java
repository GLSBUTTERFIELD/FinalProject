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

class GameTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Game game;

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
		game = em.find(Game.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		game = null;
		em.close();
	}
	
	
	@Test
	void test_Game_basic_mapping() {
		assertNotNull(game);
		assertEquals("Twister", game.getName());
	}

	@Test
	void test_Game_Gathering_ManyToMany_mapping() {
		assertNotNull(game.getGatherings());
		assertTrue(game.getGatherings().size() > 0);
	}
	
	@Test
	void test_Game_Category_ManyToMany_mapping() {
		assertNotNull(game.getCategories());
		assertTrue(game.getCategories().size() > 0);
	}
	
	@Test
	void test_Game_InventoryItem_OneToMany_mapping() {
		assertNotNull(game.getInventoryItems());
		assertTrue(game.getInventoryItems().size() > 0);
	}
	
	@Test
	void test_Game_GameResource_OneToMany_mapping() {
		assertNotNull(game.getResources());
		assertTrue(game.getResources().size() > 0);
	}
}
