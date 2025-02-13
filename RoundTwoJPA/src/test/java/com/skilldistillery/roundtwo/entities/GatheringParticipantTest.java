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

class GatheringParticipantTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private GatheringParticipant participant;

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
		participant = em.find(GatheringParticipant.class, new GatheringParticipantId(1,3));
	}

	@AfterEach
	void tearDown() throws Exception {
		participant = null;
		em.close();
	}

	@Test
	void test_GatheringParticipant_mapping() {
		assertNotNull(participant);
		assertEquals(3, participant.getParticipantRating());
		assertEquals(2, participant.getHostRating());
	}

}
