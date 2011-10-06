package com.github.solitaire.recipes.model;

import java.util.List;

public interface RecipeDAO
{
	public Recipe find(final Long id);
	public List<Recipe> findAll();
	public void create(final Recipe recipe);
	public void delete(final Recipe recipe);
	public void update(final Recipe recipe);
}
