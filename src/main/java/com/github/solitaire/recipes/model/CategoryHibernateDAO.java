package com.github.solitaire.recipes.model;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.github.solitaire.recipes.db.SessionUtil;

/**
 * Provides basic CRUD options for Category objects
 * 
 * @author Anna Stępień
 * @version 01-10-2011
 *
 */
public class CategoryHibernateDAO implements CategoryDAO
{
	private SessionFactory factory = SessionUtil.getSessionFactory();
	private Session session;
	private Transaction transaction;
	
	public Category find(final long id)
	{
		Category category = null;
		try 
		{
			session = factory.openSession();
			transaction = session.beginTransaction();
			category = (Category) session.get(Category.class, id);
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
		return category;
	}

	public Category findByName(final String name)
	{
		Category category = null;
		try 
		{
			session = factory.openSession();
			transaction = session.beginTransaction();
			category = (Category)session.createCriteria(Category.class).add(Restrictions.eq("name", name)).uniqueResult();
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
		return category;
	}

	@SuppressWarnings("unchecked")
	public List<Category> findAll()
	{
		List<Category> categories = null;
		try 
		{
			session = factory.openSession();
			transaction = session.beginTransaction();
			categories = (List<Category>)session.createSQLQuery("SELECT * FROM categories").addEntity(Category.class).list();
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
		return categories;
	}

	public void create(final Category category)
	{
		try 
		{
			session = factory.openSession();
			transaction = session.beginTransaction();
			session.save(category);
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

	public void delete(Category category)
	{
		try 
		{
			session = factory.openSession();
			transaction = session.beginTransaction();
			session.delete(category);
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

	public void update(Category category)
	{
		try 
		{
			session = factory.openSession();
			transaction = session.beginTransaction();
			session.update(category);
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
