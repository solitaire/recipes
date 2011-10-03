package com.github.solitaire.recipes.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "recipes")
public class Recipe
{
	private long id;
	private String name;
	private String instructions;
	private Set<Ingredient> ingredients = new HashSet<Ingredient>();
	private Set<Category> categories = new HashSet<Category>();
	
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
	
	@OneToMany(orphanRemoval=true, fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	public Set<Ingredient> getIngredients()
	{
		return ingredients;
	}
	
	public void setIngredients(final Set<Ingredient> ingredients)
	{
		this.ingredients = ingredients;
	}
	
	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name = "recipes_categories", 
			joinColumns = { @JoinColumn(name = "recipe_id", nullable = true) }, 
			inverseJoinColumns = { @JoinColumn(name = "category_id",nullable = true) }) 
	public Set<Category> getCategories()
	{
		return categories;
	}
	
	public void setCategories(final Set<Category> categories)
	{
		this.categories = categories;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((instructions == null) ? 0 : instructions.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recipe other = (Recipe) obj;
		if (ingredients == null) {
			if (other.ingredients != null)
				return false;
		}
		else if (!ingredients.equals(other.ingredients))
			return false;
		if (instructions == null) {
			if (other.instructions != null)
				return false;
		}
		else if (!instructions.equals(other.instructions))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;
		return true;
	}
		
}
