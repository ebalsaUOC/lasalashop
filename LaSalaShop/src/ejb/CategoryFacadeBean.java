package ejb;

import java.util.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import jpa.CategoryJPA;
import ejb.CategoryFacadeRemote;

/**
 * EJB Session Bean Class 
 */
@Stateless
public class CategoryFacadeBean implements CategoryFacadeRemote {
	
	
	public CategoryFacadeBean() {
		super();
	}

	//Persistence Unit Context
	@PersistenceContext(unitName="EPCSD_Practica") 
	private EntityManager entman;
	
	/**
	 * Method that add new Category
	 */	
	public void addCategory(String name, String description) 
	{
		CategoryJPA cat = new CategoryJPA();
		cat.setName(name);
		cat.setDescription(description);
		entman.persist(cat);		
	}

	public void updateCategory(int id, String name, String description) {

		CategoryJPA cat = entman.find(CategoryJPA.class, id); 
		cat.setId(id);
		cat.setName(name);
		cat.setDescription(description);	
		entman.persist(cat);	
	}
	
	/**
	 * Method that returns Collection of all categories
	 */
	@Override
	public Collection<?> listAllCategories() {
		@SuppressWarnings("unchecked")
		Collection<CategoryJPA> allCategories = entman.createQuery("from CategoryJPA order by Id").getResultList();
		
		return allCategories;
	}

	@Override
	public CategoryJPA showCategory(int categoryId) {
		//@SuppressWarnings("unchecked")
		CategoryJPA cat = entman.find(CategoryJPA.class, categoryId);		
		return cat;
	}
  
	
}
