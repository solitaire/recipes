package com.github.solitaire.recipes.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.github.solitaire.recipes.db.SessionUtil;

import java.lang.reflect.ParameterizedType;

public abstract class BasicHibernateDAO<ObjectType, PrimaryKeyType extends Serializable>
{
	private SessionFactory factory = SessionUtil.getSessionFactory();
	private Session session;
	private Transaction transaction;
	private Class<ObjectType> type;
	
	public BasicHibernateDAO()
	{
		this.type = (Class<ObjectType>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public ObjectType find(final PrimaryKeyType id)
	{
		ObjectType object = null;
		try 
		{
			session = factory.openSession();
			transaction = session.beginTransaction();
			object = (ObjectType) session.get(type, id);
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
		return object;		
	}
	
	public List<ObjectType> findAll()
	{
		List<ObjectType> objects = null;
		try 
		{
			session = factory.openSession();
			transaction = session.beginTransaction();
			objects = (List<ObjectType>)session.createSQLQuery("SELECT * FROM categories").addEntity(Category.class).list();
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
		return objects;		
	}
	
	public void create(final ObjectType object)
	{
		try 
		{
			session = factory.openSession();
			transaction = session.beginTransaction();
			session.save(object);
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
	
	public void update(final ObjectType object)
	{
		try 
		{
			session = factory.openSession();
			transaction = session.beginTransaction();
			session.update(object);
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
	
	public void delete(final ObjectType object)
	{
		try 
		{
			session = factory.openSession();
			transaction = session.beginTransaction();
			session.delete(object);
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
