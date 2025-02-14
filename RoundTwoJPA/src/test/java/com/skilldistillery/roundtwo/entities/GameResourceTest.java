package com.skilldistillery.roundtwo.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class GameResourceTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private GameResource gameResource;

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
		gameResource = em.find(GameResource.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		gameResource = null;
		em.close();
	}

	@Test
	void test_GameResource_mapping() {
		assertNotNull(gameResource);
		assertEquals("Twister Rules", gameResource.getName());
	}
	
	@Test
	void test_GameResource_Game_ManyToOne_mapping() {
		assertNotNull(gameResource.getGame());
		assertEquals("Twister", gameResource.getGame().getName());
	}
	
	@Test
	void test_GameResource_User_ManyToOne_mapping() {
		User user = new User();
		user.setId(3);
		assertNotNull(gameResource.getUser());
		assertEquals("Gabby", gameResource.getUser().getFirstName());
	}

}
