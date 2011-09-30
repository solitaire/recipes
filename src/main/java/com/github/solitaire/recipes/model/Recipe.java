package com.github.solitaire.recipes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "recipes")
public class Recipe
{
	private long id;
	private String name;
	private String instructions;
	
	@Id
	@GeneratedValue
	public long getId()
	{
		return id;
	}
	
	@Column(name = "id")
	public void setId(long id)
	{
		this.id = id;
	}
	
	@Column(name = "name")
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getInstructions()
	{
		return instructions;
	}
	
	public void setInstructions(String instructions)
	{
		this.instructions = instructions;
	}
		
}
