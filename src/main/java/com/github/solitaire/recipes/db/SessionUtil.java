package com.github.solitaire.recipes.db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Provides simple interface for Hibernate Session Factory
 * 
 * @author Anna Stępień
 * @version 30-09-2011
 *
 */
public class SessionUtil
{
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory()
	{
		try
		{
			return new Configuration().configure().buildSessionFactory();
			
		}
		catch(Throwable exception)
		{
			 throw new ExceptionInInitializerError(exception);
		}
	}
	
	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
}
