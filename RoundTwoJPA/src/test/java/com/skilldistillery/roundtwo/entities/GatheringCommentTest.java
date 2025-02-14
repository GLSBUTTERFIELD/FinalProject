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
		gatheringComment = em.find(GatheringComment.class, 2);
	}

	@AfterEach
	void tearDown() throws Exception {
		gatheringComment = null;
		em.close();
	}

//	@Test
//	void test_GatheringComment_mapping() {
//		assertNotNull(gatheringComment);
//		assertEquals("fs, he really does", gatheringComment.getComment());
//	}
//	
//	@Test
//	void test_GatheringComment_User_ManyToOne_Relationship() {
//		assertNotNull(gatheringComment.getParentComment());
//		assertEquals("ya got that funk", gatheringComment.getParentComment().getComment());
//	}

//	@Test
//	void test_GatheringComment_To_Gathering_ManyToOne_Relationship() {
//		assertNotNull(gatheringComment.getGathering());
//		assertEquals("Twister Meetup", gatheringComment.getGathering().getName());
//	}

	@Test
	void test_ParentComment_OneToMany_SubComment_Relationship() {
		assertNotNull(gatheringComment.getSubComment());
		assertTrue(gatheringComment.getSubComment().size() > 0);
	}
	
	@Test
	void test_SubComment_ManyToOne_SubComment_Relationship() {
		gatheringComment = em.find(GatheringComment.class, 3);
		assertNotNull(gatheringComment.getParentComment());
		assertEquals(2, gatheringComment.getParentComment().getId());
	}
	
}
