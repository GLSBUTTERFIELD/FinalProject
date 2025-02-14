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

class GatheringTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Gathering gathering;

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
		gathering = em.find(Gathering.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		gathering = null;
		em.close();
	}

	@Test
	void test_Gathering_mapping() {
		assertNotNull(gathering);
		assertEquals("Twister Meetup", gathering.getName());
		assertEquals(2, gathering.getMinParticipants());
	}
	@Test
	void test_Gathering_ManyToOne_mapping_to_address() {
		assertNotNull(gathering.getAddress());
		assertEquals("My Basement", gathering.getAddress().getName());
	}
	@Test
	void test_Gathering_OneToMany_mapping_to_GatheringParticipant() {
		assertNotNull(gathering.getParticipants());
		assertTrue(gathering.getParticipants().size()>0);
	}
	
	@Test
	void test_Gathering_Game_ManyToMany_mapping() {
		assertNotNull(gathering.getGames());
		assertTrue(gathering.getGames().size() > 0);
	}
	
	@Test
	void test_Gathering_User_ManyToOne_mapping() {
		assertNotNull(gathering.getHost());
		assertEquals("will", gathering.getHost().getFirstName());
	}
	
	@Test
	void test_Gathering_GatheringComments_OneToMany_mapping() {
		assertNotNull(gathering.getComments());
		assertTrue(gathering.getComments().size() > 0);
	}

}
