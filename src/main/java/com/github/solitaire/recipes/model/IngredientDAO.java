package com.github.solitaire.recipes.model;

public interface IngredientDAO
{
	public Ingredient find(final long id);
	public void create(final Ingredient ingredient);
	public void update(final Ingredient ingredient);
	public void delete(final Ingredient ingredient);
}
