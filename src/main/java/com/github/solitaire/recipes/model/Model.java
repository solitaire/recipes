package com.github.solitaire.recipes.model;

import java.util.List;

/**
 * Provides facade interface for all DAO classes and business logic of the application
 * Allows to change default DAO implementation 
 * 
 * @author Anna Stępień
 * @version 01-10-2011
 *
 */
public class Model
{
	private RecipeDAO recipeDAO;
	private IngredientDAO ingredientDAO;
	private CategoryDAO categoryDAO;
	
	/**
	 * Uses default DAO objects
	 */
	public Model()
	{
		recipeDAO = new RecipeHibernateDAO();
		ingredientDAO = new IngredientHibernateDAO();
		categoryDAO = new CategoryHibernateDAO();
	}
	
	/**
	 * Uses customized DAO objects
	 * 
	 * @param recipeDAO
	 * @param ingredientDAO
	 * @param categoryDAO
	 */
	public Model(final RecipeDAO recipeDAO, final IngredientDAO ingredientDAO, final CategoryDAO categoryDAO)
	{
		this.recipeDAO = recipeDAO;
		this.ingredientDAO = ingredientDAO;
		this.categoryDAO = categoryDAO;
	}
	
	public List<Recipe> getAllRecipes()
	{
		return recipeDAO.findAll();
	}
	
	public Recipe getRecipe(final long id)
	{
		return recipeDAO.find(id);
	}
	
	public void addRecipe(final Recipe recipe)
	{
		recipeDAO.create(recipe);
	}
	
	public void updateRecipe(final Recipe recipe)
	{
		recipeDAO.update(recipe);
	}
	
	public void deleteRecipe(final long recipeId)
	{
		Recipe recipe = recipeDAO.find(recipeId);
		recipeDAO.delete(recipe);
	}
	
	public void addIngredientToRecipe(final Recipe recipe, final Ingredient ingredient)
	{
		recipe.getIngredients().add(ingredient);
		recipeDAO.update(recipe);
	}
	
	public void addCategoryToRecipe(final Recipe recipe, final Category category)
	{
		recipe.getCategories().add(category);
		recipeDAO.update(recipe);
	}
	
	public void removeIngredientFromRecipe(final Recipe recipe, final Ingredient ingredient)
	{
		recipe.getIngredients().remove(ingredient);
		recipeDAO.update(recipe);
	}
	
	public void removeCategoryFromRecipe(final Recipe recipe, final Category category)
	{
		recipe.getCategories().remove(category);
		recipeDAO.update(recipe);
	}
	
	public List<Category> getAllCategories()
	{
		return categoryDAO.findAll();
	}
	
	public Category getCategory(final long categoryId)
	{
		return categoryDAO.find(categoryId);
	}
	
	public void addCategory(final Category category)
	{
		categoryDAO.create(category);
	}
	
	public void updateCategory(final Category category)
	{
		categoryDAO.update(category);
	}
	
	public void deleteCategory(final long categoryId)
	{
		Category category = categoryDAO.find(categoryId);
		categoryDAO.delete(category);
	}

	public List<Ingredient> getAllIngredients()
	{
		return ingredientDAO.findAll();
	}
}
