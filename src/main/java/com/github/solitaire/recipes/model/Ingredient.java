package com.github.solitaire.recipes.model;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ingredients")
public class Ingredient implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	private long id;
	private String name;
	private Measurement measurement;
	
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
	
	@Embedded
	public Measurement getMeasurement()
	{
		return measurement;
	}
	
	public void setMeasurement(final Measurement measurement)
	{
		this.measurement = measurement;
	}
	
}
