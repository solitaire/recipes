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
	
	private Long id;
	private String name;
	private Measurement measurement;
	
	@Id
	@GeneratedValue
	public Long getId()
	{
		return id;
	}
	
	public void setId(final Long id)
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

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((measurement == null) ? 0 : measurement.hashCode());
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
		Ingredient other = (Ingredient) obj;
		if (measurement == null) {
			if (other.measurement != null)
				return false;
		}
		else if (!measurement.equals(other.measurement))
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
