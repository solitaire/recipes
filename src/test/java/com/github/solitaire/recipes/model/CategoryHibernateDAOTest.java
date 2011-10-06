package com.github.solitaire.recipes.model;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CategoryHibernateDAOTest
{
	private static CategoryHibernateDAO categoryDAO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		categoryDAO = new CategoryHibernateDAO();
	}

	@Before
	public void setUp() throws Exception
	{
		Category category_one = new Category();
		Category category_two = new Category();	
		category_one.setName("Breakfast");
		category_one.setName("Dinner");
		
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(category_one);
		session.save(category_two);
		transaction.commit();
		session.close();		
	}

	@After
	public void tearDown() throws Exception
	{
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		session.createSQLQuery("DELETE FROM categories");
		transaction.commit();
		session.close();		
	}

	@Test
	public void itAddsNewCategories()
	{
		Category category = new Category();
		category.setName("Salads");
		categoryDAO.create(category);
		assertEquals("Salads", categoryDAO.find((long) 3).getName());
	}

	@Test
	public void itUpdatesCategory()
	{
		Category category = categoryDAO.find((long) 1);
		category.setName("Salads");
		categoryDAO.update(category);
		assertEquals("Salads", categoryDAO.find((long) 1).getName());
	}
	
	@Test
	public void itDeletesCategory()
	{
		Category category = categoryDAO.find((long) 1);
		categoryDAO.delete(category);
		assertNull(categoryDAO.find((long) 1));
	}
	
	@Test
	public void itFindsCategoryByName()
	{
		Category category = categoryDAO.findByName("Dinner");
		assertNotNull(category);
		assertEquals("Dinner", category.getName());
	}
}
