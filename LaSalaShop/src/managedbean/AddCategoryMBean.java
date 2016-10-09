/* Copyright FUOC.  All rights reserved.
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
* Managed Bean AddCategoryMBean
*/
@ManagedBean(name = "addCategory")
@SessionScoped
public class AddCategoryMBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@EJB
	private CategoryFacadeRemote catFacade;
	protected CategoryJPA dataCat;
	protected Integer id;
	protected String name;
	protected String description;
	
	public AddCategoryMBean() throws Exception {
		
	}
		
	public void setId (Integer id){
		this.id = id;	
	}
	public Integer getId (){
		return this.id;
	}
	
	public void setName (String name){
		this.name = name;;
	}
	public String getName (){
		return this.name;
	}
	
	public void setDescription (String description){
		this.description = description;
	}
	public String getDescription (){
		return this.description;
	}
	
	public void edit () {
		dataCat = new CategoryJPA();		
		dataCat.setId(null);
		dataCat.setName(null);
		dataCat.setDescription(null);
	}
					
	public void AddCategory() throws Exception
	{	
		System.out.println("Add Managed Bean - Name: " + name + " Desciption: " + description);
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		catFacade = (CategoryFacadeRemote) ctx.lookup("java:app/EPCSD_Practica.jar/CategoryFacadeBean!ejb.CategoryFacadeRemote");
		catFacade.addCategory(this.name, this.description);	
		
	}
}
