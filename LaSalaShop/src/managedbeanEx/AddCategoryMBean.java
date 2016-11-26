/* Copyright FUOC.  All rights reserved.
 * @author Marc Colom Royo, 2014
 */
package managedbeanEx;

import java.io.Serializable;
import java.util.*;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;

import jpaEx.CategoryJPA;
import ejbEx.CategoryFacadeRemote;
import ejbEx.OpStatus;

/**
 * create cdominguez Managed Bean AddCategoryMBean
 */
@ManagedBean(name = "addCategory")
@RequestScoped
public class AddCategoryMBean implements Serializable
	{
	private static final long serialVersionUID = 1L;
	@EJB
	private CategoryFacadeRemote catFacade;
	protected CategoryJPA dataCat;
	protected Integer id;
	protected String name;
	protected String description;
	protected String errMsg;

	public AddCategoryMBean() throws Exception
		{
		FacesContext facesContext = FacesContext.getCurrentInstance();
	    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		}

	public void setId(Integer id)
		{
		this.id = id;
		}

	public Integer getId()
		{
		return this.id;
		}

	public void setName(String name)
		{
		this.name = name;
		}

	public String getName()
		{
		return this.name;
		}

	public void setDescription(String description)
		{
		this.description = description;
		}

	public String getDescription()
		{
		return this.description;
		}

	public void edit()
		{
		dataCat = new CategoryJPA();
		dataCat.setId(null);
		dataCat.setName(null);
		dataCat.setDescription(null);
		}

	public String  AddCategory() throws Exception
		{
		
		if(this.name==null || "".equals(this.name.trim()) ||
				this.description==null || "".equals(this.description.trim()) ){
			
			this.errMsg = "Ompli tots els camps si us plau";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.errMsg));
			return "addCategoryView";
		}		
		
		System.out.println("Add Managed Bean - Name: " + name + " Desciption: " + description);
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		catFacade = (CategoryFacadeRemote) ctx.lookup("java:app/e-agenda.jar/CategoryFacadeBean!ejb.CategoryFacadeRemote");
		dataCat = new CategoryJPA(this.name, this.description);
		OpStatus op = catFacade.addCategory(dataCat);
			
		
		if ("OK".equals(op.getCod()))
			{
			return "categoryListView";
			}
		else
			{
			this.errMsg = op.getMsg();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.errMsg));
			return "addCategoryView";
			}
		}
		
	}
