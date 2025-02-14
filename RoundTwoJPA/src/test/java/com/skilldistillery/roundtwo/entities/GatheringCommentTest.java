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

class GatheringCommentTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private GatheringComment gatheringComment;

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
		gatheringComment = em.find(GatheringComment.class, 3);
	}

	@AfterEach
	void tearDown() throws Exception {
		gatheringComment = null;
		em.close();
	}

	@Test
	void test_GatheringComment_mapping() {
		assertNotNull(gatheringComment);
		assertEquals("fs, he really does", gatheringComment.getComment());
	}
	
	@Test
	void test_GatheringComment_User_ManyToOne_Relationship() {
		assertNotNull(gatheringComment.getParentComment());
		assertEquals("ya got that funk", gatheringComment.getParentComment().getComment());
	}

}
