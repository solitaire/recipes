package com.github.solitaire.recipes.model;

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
public class CategoryHibernateDAO extends BasicHibernateDAO<Category, Long> implements CategoryDAO
{
	private SessionFactory factory = SessionUtil.getSessionFactory();
	private Session session;
	private Transaction transaction;

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

}
