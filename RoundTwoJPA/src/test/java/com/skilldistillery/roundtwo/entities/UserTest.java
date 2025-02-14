package com.skilldistillery.roundtwo.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class UserTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;

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
		user = em.find(User.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		user = null;
		em.close();
	}

	@Test
	void test_User_mapping() {
		assertNotNull(user);
		assertEquals("test", user.getUsername());
		assertEquals("will", user.getFirstName());
	}
	
	@Test
	void test_User_ManyToOne_mapping_to_Address() {
		assertNotNull(user.getAddress());
		assertEquals("My Basement", user.getAddress().getName());
	}
	
	@Test
	void test_User_OneToMany_mapping_to_GatheringParticipant() {
		assertNotNull(user.getGatheringsAttended());
		assertTrue(user.getGatheringsAttended().size()>0);
	}

	@Test
	void test_User_OneToMany_mapping_to_GatheringComment() {
		assertNotNull(user.getGatheringComments());
		assertTrue(user.getGatheringComments().size()>0);
	}
	
	@Test
	void test_User_OneToMany_mapping_to_GameResource() {
		user = em.find(User.class, 3);
		assertNotNull(user.getResources());
		assertTrue(user.getResources().size() > 0);
	}
	
	@Test
	void test_User_OneToMany_mapping_to_InventoryItem() {
		assertNotNull(user.getInventoryItems());
		assertTrue(user.getInventoryItems().size()>0);
	}
	
	@Test
	void test_User_OneToMany_mapping_to_InventoryItemComment() {
		assertNotNull(user.getInventoryItemComments());
		assertTrue(user.getInventoryItemComments().size()>0);
	}
	
	@Test
	void test_User_OneToMany_mapping_to_Gathering() {
		assertNotNull(user.getGatheringsHosted());
		assertTrue(user.getGatheringsHosted().size()>0);
	}
	
	@Test
	void test_User_ManyToMany_mapping_to_Game() {
		assertNotNull(user.getFavoriteGames());
		assertTrue(user.getFavoriteGames().size() > 0);
	}
	
	
}
