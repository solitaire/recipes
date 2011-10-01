package com.github.solitaire.recipes.model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.github.solitaire.recipes.db.SessionUtil;

/**
 * Basic CRUD options for ingredient objects
 * 
 * @author Anna Stępień
 * @version 01-10-2011
 *
 */
public class IngredientHibernateDAO implements IngredientDAO
{
	private SessionFactory factory = SessionUtil.getSessionFactory();
	private Session session;
	private Transaction transaction;
	
	public Ingredient find(long id)
	{
		Ingredient ingredient = null;
		try 
		{
			session = factory.openSession();
			transaction = session.beginTransaction();
			ingredient = (Ingredient) session.get(Ingredient.class, id);
			transaction.commit();
		}
		catch (HibernateException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
		return ingredient;
	}

	public void create(Ingredient ingredient)
	{
		try
		{
			session = factory.openSession();
			transaction = session.beginTransaction();
			session.save(ingredient);
			transaction.commit();
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
	}

	public void update(Ingredient ingredient)
	{
		try
		{
			session = factory.openSession();
			transaction = session.beginTransaction();
			session.update(ingredient);
			transaction.commit();
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
	}

	public void delete(Ingredient ingredient)
	{
		try
		{
			session = factory.openSession();
			transaction = session.beginTransaction();
			session.delete(ingredient);
			transaction.commit();
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
	}
}
