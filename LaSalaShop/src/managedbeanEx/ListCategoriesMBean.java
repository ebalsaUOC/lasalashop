package managedbeanEx;

import java.util.*;

import javax.ejb.EJB;
import javax.faces.bean.*;
import javax.naming.Context;
import javax.naming.InitialContext;

import jpaEx.CategoryJPA;
import ejbEx.CategoryFacadeRemote;




/**
 * Create cdominguez
 * Managed Bean ListCategoriesMBean
 */
@ManagedBean(name = "listCategories")
@SessionScoped
public class ListCategoriesMBean {
	
	@EJB
	private CategoryFacadeRemote categories;
	protected List<CategoryJPA> categoryList = new ArrayList<CategoryJPA>();
	protected List<CategoryJPA> categoryListView;
	
	public ListCategoriesMBean() throws Exception
	{
		this.categoryList();
	}

	/**
	 * Method get which return Categories Collection
	 * @return Collection
	 */
	public Collection<CategoryJPA> getCategoryList()
	{
		return categoryList;
	}
	
	/**
	 * Method that takes a collection of instances of CategoryJPA calling 
	 * method listAllCategories of the EJB Session
	 * @throws Exception
	 */
	public String categoryList() throws Exception
	{
	Properties props = System.getProperties();
	Context ctx = new InitialContext(props);
	categories = (CategoryFacadeRemote) ctx.lookup("java:app/e-agenda.jar/CategoryFacadeBean!ejb.CategoryFacadeRemote");
	List<CategoryJPA> lista = categories.listAllCategories();
	if (lista != null && lista.size() > 0)
		{
		for (CategoryJPA e : lista)
			{
			this.categoryList.add(e);
			}
		}
	else
		{
		return "errorView";
		}
	return "";
	}

public List<CategoryJPA> getCategoryListView() throws Exception
	{
	Properties props = System.getProperties();
	Context ctx = new InitialContext(props);
	categories = (CategoryFacadeRemote) ctx.lookup("java:app/e-agenda.jar/CategoryFacadeBean!ejb.CategoryFacadeRemote");
	categoryListView = (List<CategoryJPA>) categories.listAllCategories();
	return categoryListView;
	}
}
