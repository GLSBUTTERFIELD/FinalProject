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

class GameCommentTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private GameComment comment;

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
		comment = em.find(GameComment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		comment = null;
		em.close();
	}

//	@Test
//	void test_GameComment_mapping() {
//		assertNotNull(comment);
//		assertEquals("who even plays twister anymore?", comment.getComment());
//	}
	
//	@Test
//	void test_GameSubComment_ManyToOne_ParentComment_Relations() {
//		assertNotNull(comment.getParentComment());
//		assertEquals("who even plays twister anymore?", comment.getParentComment().getComment());
//		}
	
	@Test
	void test_GameParentComment_OneToMany_SubComment_Relationship() {
		assertNotNull(comment.getSubComment());
		assertTrue(comment.getSubComment().size() > 0);
	}

//	@Test
//	void test_GameComment_To_Game_ManyToOne_Relationship() {
//		assertNotNull(comment.getGame());
//		assertEquals("Twister", comment.getGame().getName());
//	}
	
	@Test
	void test_GameComment_To_User_ManyToOne_Relationship() {
		assertNotNull(comment.getUser());
		assertEquals("Player2", comment.getUser().getUsername());
	}
	
	


}
