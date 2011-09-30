package com.github.solitaire.recipes.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Measurement implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private Double amount;
	
	@Column(name = "measurement_name")
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	@Column(name = "measure_amount")
	public Double getAmount()
	{
		return amount;
	}
	
	public void setAmount(Double amount)
	{
		this.amount = amount;
	}	
}