package com.github.solitaire.recipes.model;

import java.util.List;

public interface CategoryDAO
{
	public Category find(final Long id);
	public Category findByName(final String name);
	public List<Category> findAll();
	public void create(final Category category);
	public void delete(final Category category);
	public void update(final Category category);
}
