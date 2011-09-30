package com.github.solitaire.recipes.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "recipes")
public class Recipe
{
	private long id;
	private String name;
	private String instructions;
	private List<Ingredient> ingredients = new LinkedList<Ingredient>();
	
	@Id
	@GeneratedValue
	public long getId()
	{
		return id;
	}
	
	public void setId(final long id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(final String name)
	{
		this.name = name;
	}
	
	public String getInstructions()
	{
		return instructions;
	}
	
	public void setInstructions(final String instructions)
	{
		this.instructions = instructions;
	}
	
	@OneToMany(fetch = FetchType.EAGER)
	public List<Ingredient> getIngredients()
	{
		return ingredients;
	}
	
	public void setIngredients(final List<Ingredient> ingredients)
	{
		this.ingredients = ingredients;
	}
		
}
