/*
 * 
 * @author falarconm@uoc.edu
 */
package managedbeanEx;

import java.io.Serializable;
import java.util.*;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import jpaEx.CategoryJPA;
import jpaEx.EventJPA;
import jpaEx.LanguageJPA;
import ejbEx.AdministrationFacadeRemote;
import ejbEx.EventFacadeRemote;
import ejbEx.OpStatus;

/**
 * Managed Bean AddCategoryToEvent
 */
@ManagedBean(name = "addCategoryToEvent")
@RequestScoped
public class AddCategoryToEventMBean implements Serializable
	{
	private static final long serialVersionUID = 1L;
	@EJB
	private AdministrationFacadeRemote adminRemote;
	protected String errMsg;
	protected List<CategoryJPA> categories = new ArrayList<CategoryJPA>();
	// Hemos encontrado esta forma de mapear pares de valores, en este caso id y descripcion
	private Map<String, CategoryJPA> categoriesList;
	private CategoryJPA selectedCategory;

	public AddCategoryToEventMBean() throws Exception
		{
		this.setCategoriesList(new LinkedHashMap<String, CategoryJPA>());
		this.categoriesList();
		}

	public String addCategory(EventJPA event, CategoryJPA category) throws Exception
		{
		System.out.println("Add Category: " + category.getId() + " to Event: " + Integer.toString(event.getId()));
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		adminRemote = (AdministrationFacadeRemote) ctx.lookup("java:app/e-agenda.jar/AdministrationFacadeBean!ejb.AdministrationFacadeRemote");
		OpStatus op = new OpStatus();
		op = adminRemote.addCategoryToEvent(event, category);
		if ("OK".equals(op.getCod()))
			return "eventsListView";
		else
			{
			this.errMsg = op.getMsg();
			return "errorView";
			}
		}

	public String getErrMsg()
		{
		return errMsg;
		}

	public void setErrMsg(String errMsg)
		{
		this.errMsg = errMsg;
		}

	public static long getSerialversionuid()
		{
		return serialVersionUID;
		}

	public void categoriesList() throws NamingException
		{
		System.out.println("[AddCategoryToEventMBean] Voy a buscar todas las categorias");
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		adminRemote = (AdministrationFacadeRemote) ctx.lookup("java:app/e-agenda.jar/AdministrationFacadeBean!ejb.AdministrationFacadeRemote");
		List<CategoryJPA> categoryCollection = (List<CategoryJPA>) adminRemote.listCategories();
		this.categories = categoryCollection;
		if (this.categories != null && this.categories.size() > 0)
			{
			for (CategoryJPA cat : this.categories)
				{
				this.getCategoriesList().put(cat.getDescription(), cat);
				}
			}
		}

	public void categoryValueChanged(ValueChangeEvent categoryChanged)
		{
		this.setSelectedCategory((CategoryJPA) categoryChanged.getNewValue());
		}

	public Map<String, CategoryJPA> getCategoriesList()
		{
		return categoriesList;
		}

	public void setCategoriesList(Map<String, CategoryJPA> categoriesList)
		{
		this.categoriesList = categoriesList;
		}

	public CategoryJPA getSelectedCategory()
		{
		return selectedCategory;
		}

	public void setSelectedCategory(CategoryJPA selectedCategory)
		{
		if(selectedCategory == null)
			{
			System.out.println("Al seleccionar la categoria se queda en nulo");
			}
		else
			{
			System.out.println("Seleccionamos categoria con id " + selectedCategory.getId());
			}
		this.selectedCategory = selectedCategory;
		}
	}
