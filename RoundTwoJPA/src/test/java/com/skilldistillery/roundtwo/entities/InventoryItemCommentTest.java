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

class InventoryItemCommentTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private InventoryItemComment comment;

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
		comment = em.find(InventoryItemComment.class, 2);
	}

	@AfterEach
	void tearDown() throws Exception {
		comment = null;
		em.close();
	}

//	@Test
//	void test_InventoryItemComment_mapping() {
//		assertNotNull(comment);
//		assertEquals("why do you have an unreleased version?", comment.getComment());
//		
//	}
	
//	@Test
//	void test_InventoryItemSubComment_ManyToOne_ParentComment_Relations() {
//		assertNotNull(comment.getParentComment());
//		assertEquals("why do you have an unreleased version?", comment.getParentComment().getComment());
//	}
//	
	@Test
	void test_InventoryItemParentComment_OneToMany_SubComment_Relationship() {
		assertNotNull(comment.getSubComment());
		assertTrue(comment.getSubComment().size() > 0);
	}
	
	@Test
	void test_InventoryItemComment__InventoryItem_ManyToOne_mapping() {
		assertNotNull(comment.getInventoryItem());
		assertEquals("Twister Delux Edition", comment.getInventoryItem().getName());
	}

	@Test
	void test_InventoryItemComment__User_ManyToOne_mapping() {
		assertNotNull(comment.getUser());
		assertEquals("will", comment.getUser().getFirstName());
	}

}
