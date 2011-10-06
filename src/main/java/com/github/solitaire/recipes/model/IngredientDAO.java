package com.github.solitaire.recipes.model;

import java.util.List;

public interface IngredientDAO
{
	public Ingredient find(final Long id);
	public List<Ingredient> findAll();
	public void create(final Ingredient ingredient);
	public void update(final Ingredient ingredient);
	public void delete(final Ingredient ingredient);
}
