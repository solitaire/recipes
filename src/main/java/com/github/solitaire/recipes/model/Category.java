package com.github.solitaire.recipes.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "categories")
public class Category implements Serializable
{
	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private Set<Recipe> recipes = new HashSet<Recipe>();
	
	@Id
	@GeneratedValue
	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	@ManyToMany(mappedBy = "categories")
	public Set<Recipe> getRecipes()
	{
		return recipes;
	}
	
	public void setRecipes(final Set<Recipe> recipes)
	{
		this.recipes = recipes;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
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
		Category other = (Category) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;
		return true;
	}
}
