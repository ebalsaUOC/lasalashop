/*
 * Copyright FUOC.  All rights reserved.
 * @author Marc Colom Royo, 2014
 */
package managedbean;

import java.io.Serializable;
import java.util.*;
import javax.ejb.EJB;
import javax.faces.bean.*;
import javax.naming.Context;
import javax.naming.InitialContext;

import jpa.CategoryJPA;
import ejb.CategoryFacadeRemote;

/**
 * Managed Bean ShowCategoryMBean
 */
@ManagedBean(name = "show")
@SessionScoped
public class ShowCategoryMBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@EJB
	private CategoryFacadeRemote showCategory;
	protected CategoryJPA dataCategory = new CategoryJPA();
	
	
	public ShowCategoryMBean() throws Exception	{
	
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
		dataCategory.setId(cat.getId());
		dataCategory.setName(cat.getName());
		dataCategory.setDescription(cat.getDescription());
	}
	
	public CategoryJPA getDataCategory()
	{
		return dataCategory;
	}
}