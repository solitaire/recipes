package com.github.solitaire.recipes.model;

import static org.junit.Assert.assertEquals;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RecipeHibernateDAOTest
{
	private static RecipeHibernateDAO recipeDAO;
	
	@BeforeClass
	public static void initialize()
	{
		recipeDAO = new RecipeHibernateDAO();
	}
	
	@Before
	public void setUp()
	{	
		Recipe recipe_one = new Recipe();
		Recipe recipe_two = new Recipe();	
		recipe_one.setId(1);
		recipe_one.setName("Shrimps");
		recipe_one.setInstructions("Instructions");
		recipe_two.setId(2);
		recipe_two.setName("Salad");
		recipe_two.setInstructions("Instructions");		
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(recipe_one);
		session.save(recipe_two);
		transaction.commit();
		session.close();
	}
	
	@After
	public void tearDown()
	{
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		session.createSQLQuery("DELETE FROM recipes");
		transaction.commit();
		session.close();
	}
	
	@Test
	public void itAddsNewRecipes()
	{	
		Recipe recipe = new Recipe();
		recipe.setName("Spaghetti");
		recipe.setInstructions("Make it");
		recipeDAO.create(recipe);
		assertEquals(3, recipeDAO.findAll().size());
	}
	
	@Test
	public void itDeletesRecipe()
	{
		Recipe recipe = recipeDAO.find(1);
		recipeDAO.delete(recipe);
		assertEquals(1, recipeDAO.findAll().size());
	}
	
	@Test
	public void itUpdatesRecipe()
	{
		Recipe recipe = recipeDAO.find(1);
		recipe.setName("Lobsters");
		recipeDAO.update(recipe);
		assertEquals(2, recipeDAO.findAll().size());
		assertEquals("Lobsters", recipeDAO.find(1).getName());
	}

}
