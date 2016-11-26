/*
 * Copyright FUOC.  All rights reserved.
 * @author Marc Colom Royo, 2014
 */
package managedbeanEx;

import java.io.Serializable;
import java.util.*;

import javax.ejb.EJB;
import javax.faces.bean.*;
import javax.naming.Context;
import javax.naming.InitialContext;

import jpaEx.CategoryJPA;
import ejbEx.CategoryFacadeRemote;
import ejbEx.OpStatus;

/**
 * Create cdominguez
 * Managed Bean ModifyCategoryMBean
 */
@ManagedBean(name = "modifyCategory")
@SessionScoped
public class ModifyCategoryMBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@EJB
	private CategoryFacadeRemote modifyCategory;
	protected CategoryJPA dataCategory;
	protected Integer id;
	protected String name;
	protected String description;
	protected String errMsg; 
	
	
	public ModifyCategoryMBean() throws Exception 
	{
		
	}
	
	/**
	 * Get/set the id number and CategoryJPA instance
	 * @return Category Id
	 */
	public int getId()
	{
		return dataCategory.getId();
	}
	public void setId(int id)
	{
		dataCategory.setId(id);	
	}
	
	public String getName()
	{
		return dataCategory.getName();
	}
	public void setName(String name)
	{
		dataCategory.setName(name);	
	}
	
	public String getDescription()
	{
		return dataCategory.getDescription();
	}
	
	public void setDescription(String description)
	{
		dataCategory.setDescription(description);	
	}
	
	public void edit (CategoryJPA cat) {
		dataCategory = new CategoryJPA();
		dataCategory.setId(cat.getId());
		dataCategory.setName(cat.getName());
		dataCategory.setDescription(cat.getDescription());
	}
	
	public CategoryJPA getDataCategory()
	{
		return dataCategory;
	}	
	
	public String modifyDataCategory() throws Exception
	{	
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		modifyCategory = (CategoryFacadeRemote) ctx.lookup("java:app/e-agenda.jar/CategoryFacadeBean!ejb.CategoryFacadeRemote");
		OpStatus op= modifyCategory.updateCategory(dataCategory.getId(), dataCategory.getName(), dataCategory.getDescription());
		if("OK".equals(op.getCod())) return "categoryListView";
		else{
			this.errMsg = op.getMsg();
			return "errorView";
		}
	}
	
}
