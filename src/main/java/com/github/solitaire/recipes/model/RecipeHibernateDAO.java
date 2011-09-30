package com.github.solitaire.recipes.model;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.github.solitaire.recipes.db.SessionUtil;

/**
 * Hibernate DAO interface for Recipe objects
 * It support basic CRUD operations
 * 
 * @author Anna Stępień
 * @version 30-09-2011
 *
 */
public class RecipeHibernateDAO implements RecipeDAO
{
	private SessionFactory factory = SessionUtil.getSessionFactory();
	private Session session;
	private Transaction transaction;
	
	public Recipe find(long id)
	{
		Recipe recipe = null;
		try 
		{
			session = factory.openSession();
			transaction = session.beginTransaction();
			recipe = (Recipe) session.get(Recipe.class, id);
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
		return recipe;
	}

	@SuppressWarnings("unchecked")
	public List<Recipe> findAll()
	{
		List<Recipe> recipes = null;
		try 
		{
			session = factory.openSession();
			transaction = session.beginTransaction();
			recipes = session.createSQLQuery("SELECT * FROM recipes").list();
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
		return recipes;
	}

	public void create(final Recipe recipe)
	{
		try 
		{
			session = factory.openSession();
			transaction = session.beginTransaction();
			session.save(recipe);
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
	}

	public void delete(final Recipe recipe)
	{
		try 
		{
			session = factory.openSession();
			transaction = session.beginTransaction();
			session.delete(recipe);
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
	}

	public void update(final Recipe recipe)
	{
		try 
		{
			session = factory.openSession();
			transaction = session.beginTransaction();
			session.update(recipe);
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
	}

}
