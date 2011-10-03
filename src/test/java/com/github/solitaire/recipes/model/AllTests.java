package com.github.solitaire.recipes.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CategoryHibernateDAOTest.class,
		IngredientHibernateDAOTest.class, ModelIntegrationTest.class,
		RecipeHibernateDAOTest.class })
public class AllTests
{

}
