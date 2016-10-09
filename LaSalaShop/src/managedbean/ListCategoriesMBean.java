package managedbean;

import java.io.Serializable;
import java.util.*;

import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import javax.faces.bean.*;
import javax.faces.event.ValueChangeEvent;
import javax.naming.Context;
import javax.naming.InitialContext;

import jpa.CategoryJPA;
import ejb.CategoryFacadeRemote;



/**
 * Managed Bean ListCategoriesMBean
 */
@ManagedBean(name = "list")
@SessionScoped

public class ListCategoriesMBean {
	
	@EJB private CategoryFacadeRemote categories;
	protected Collection<SelectItem> categoryList = new ArrayList<SelectItem>();
	protected Collection<CategoryJPA> categoryListView;
	
	public ListCategoriesMBean() throws Exception
	{
		this.categoryList();
	}

	/**
	 * Method get which return Categories Collection
	 * @return Collection
	 */
	public Collection<SelectItem> getCategoryList()
	{
		return categoryList;
	}
	
	/**
	 * Method that takes a collection of instances of CategoryJPA calling 
	 * method listAllCategories of the EJB Session
	 * @throws Exception
	 */
	public void categoryList() throws Exception
	{	
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		categories = (CategoryFacadeRemote) ctx.lookup("java:app/EPCSD_Practica.jar/CategoryFacadeBean!ejb.CategoryFacadeRemote");
		@SuppressWarnings("unchecked")
		Collection<CategoryJPA> categoryCollection = (Collection<CategoryJPA>) categories.listAllCategories();
		for (Iterator<CategoryJPA> iter2 = categoryCollection.iterator(); iter2.hasNext();)
		{
			CategoryJPA cat = (CategoryJPA) iter2.next();
			SelectItem item = new SelectItem(cat.getName());
			this.categoryList.add(item);			
		}
	
	}	
	
	public Collection<CategoryJPA> getCategoryListView() throws Exception
	{		
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		categories = (CategoryFacadeRemote) ctx.lookup("java:app/EPCSD_Practica.jar/CategoryFacadeBean!ejb.CategoryFacadeRemote");
		categoryListView = (Collection<CategoryJPA>)categories.listAllCategories();
		
		return categoryListView;		
	}
}
