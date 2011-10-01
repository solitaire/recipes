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

public class IngredientHibernateDAOTest
{
	private static IngredientHibernateDAO ingredientDAO;
	
	@BeforeClass
	public static void initialize()
	{
		ingredientDAO = new IngredientHibernateDAO();
	}
	@Before
	public void setUp()
	{
		Ingredient ingredient_one = new Ingredient();
		Ingredient ingredient_two = new Ingredient();	
		ingredient_one.setId(1);
		ingredient_one.setName("Cheese");
		ingredient_one.setMeasurement(new Measurement("slices", 2));
		ingredient_two.setId(2);
		ingredient_one.setName("Eggs");
		ingredient_two.setMeasurement(new Measurement("", 3));		
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(ingredient_one);
		session.save(ingredient_two);
		transaction.commit();
		session.close();		
	}

	@After
	public void tearDown()
	{
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		session.createSQLQuery("DELETE FROM ingredients");
		transaction.commit();
		session.close();		
	}
	
	@Test
	public void itAddsIngredients()
	{
		Ingredient ingredient = new Ingredient();
		ingredient.setId(3);
		ingredient.setName("Shrimps");
		ingredient.setMeasurement(new Measurement("g", 200));
		ingredientDAO.create(ingredient);
		assertEquals("Shrimps", ingredientDAO.find(3).getName());
		assertEquals("g", ingredientDAO.find(3).getMeasurement().getName());
	}
	
	@Test
	public void itUpdatesIngredient()
	{
		Ingredient ingredient = ingredientDAO.find(1);
		ingredient.setName("Apple");
		ingredientDAO.update(ingredient);
		assertEquals("Apple", ingredientDAO.find(1).getName());
	}
	
	@Test
	public void itDeletesIngredient()
	{
		Ingredient ingredient = ingredientDAO.find(1);
		ingredientDAO.delete(ingredient);
		assertNull(ingredientDAO.find(1));
	}
}
