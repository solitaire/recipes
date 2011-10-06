package com.github.solitaire.recipes.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ModelIntegrationTest
{
	private Model model;

	@Before
	public void setUp()
	{
		model = new Model();
		Recipe recipe_one = new Recipe();
		Recipe recipe_two = new Recipe();	
		recipe_one.setName("Shrimps");
		recipe_one.setInstructions("Instructions");
		recipe_two.setName("Salad");
		recipe_two.setInstructions("Instructions");	
		Category category = new Category();
		category.setName("Dinner");
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(recipe_one);
		session.save(recipe_two);
		session.save(category);
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
		session.createSQLQuery("DELETE FROM ingredients");
		session.createSQLQuery("DELETE FROM categories");
		transaction.commit();
		session.close();
	}

	@Test
	public void itFindsAllRecipes()
	{
		List<Recipe> recipes = model.getAllRecipes();
		assertEquals(2, recipes.size());
	}
	
	@Test
	public void itAddsRecipe()
	{
		Recipe recipe = new Recipe();
		recipe.setName("Broccoli");
		recipe.setInstructions("Boil");
		model.addRecipe(recipe);
		assertEquals(3, model.getAllRecipes().size());
	}
	
	@Test
	public void itUpdatesRecipe(){
		Recipe recipe = new Recipe();
		recipe.setId((long) 1);
		recipe.setName("Boiled Shrimps");
		recipe.setInstructions("Instructions");
		model.updateRecipe(recipe);
		assertEquals(2, model.getAllRecipes().size());
		assertEquals("Boiled Shrimps", model.getRecipe(1).getName());
		assertEquals("Instructions", model.getRecipe(1).getInstructions());
	}
	
	@Test
	public void itDeletesRecipe()
	{
		model.deleteRecipe(1);
		assertEquals(1, model.getAllRecipes().size());
		assertNull(model.getRecipe(1));
	}
	
	@Test
	public void itAddsIngredientToRecipe()
	{
		Recipe recipe = model.getRecipe(1);
		Ingredient ingredient_one = new Ingredient();
		ingredient_one.setName("Salt");
		ingredient_one.setMeasurement(new Measurement("spoon", 1));
		
		model.addIngredientToRecipe(recipe, ingredient_one);
		
		assertEquals(1, model.getRecipe(1).getIngredients().size());
	}
	
	@Test
	public void itRemovesAllIngredientsWhenDeletingRecipe()
	{
		Recipe recipe = model.getRecipe(1);
		Ingredient ingredient_one = new Ingredient();
		ingredient_one.setName("Salt");
		ingredient_one.setMeasurement(new Measurement("spoon", 1));
		
		model.addIngredientToRecipe(recipe, ingredient_one);
		model.deleteRecipe(1);
		
		assertEquals(0, model.getAllIngredients().size());		
	}

	@Test
	public void itAddsCategoryToRecipe()
	{
		Recipe recipe = model.getRecipe(1);
		Category category = new Category();
		category.setName("Test");
		model.addCategoryToRecipe(recipe, category);
		assertEquals(1, model.getRecipe(1).getCategories().size());
		assertEquals(2, model.getAllCategories().size());
	}
	
	@Test
	public void itRemovesRecipeCategoriesWhenDeletingCategory()
	{
		Recipe recipe = model.getRecipe(1);
		Category category = model.getCategory(1);
		model.addCategoryToRecipe(recipe, category);
		model.deleteCategory(1);
		assertEquals(0, model.getRecipe(1).getCategories().size());
		assertEquals(0, model.getAllCategories().size());
	}
}
