/*
 * @author Alex Cabrera Gil - acabreragil@uoc.edu
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

import jpaEx.CompanyJPA;
import ejbEx.CompanyFacadeRemote;
import ejbEx.OpStatus;

/**
 * Managed Bean AddCompanyMBean
 */
@ManagedBean(name = "addCompany")
@SessionScoped
public class AddCompanyMBean implements Serializable
	{
	private static final long serialVersionUID = 1L;
	@EJB
	private CompanyFacadeRemote compFacade;
	protected CompanyJPA dataComp;
	protected Integer id;
	protected String name;
	protected String errMsg;

	public AddCompanyMBean() throws Exception
		{
		}

	
	/*
	 * Getters / Setters
	 */
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

	public void edit()
		{
		dataComp = new CompanyJPA();
		dataComp.setId(null);
		dataComp.setName(null);
		}

	/**
	 * Afegeix una empresa al sistema
	 * @return la pantalla de destí corresponent
	 */
	public String AddCompany() throws Exception
		{

		if(this.name==null || "".equals(this.name.trim()) )
			{
			this.errMsg = "Ompli tots els camps si us plau";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.errMsg));
			return "addCompanyView";
			}	
	
		System.out.println("Add Managed Bean - Name: " + name);
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		compFacade = (CompanyFacadeRemote) ctx.lookup("java:app/e-agenda.jar/CompanyFacadeBean!ejb.CompanyFacadeRemote");
					
		dataComp = new CompanyJPA(this.name);
		
		OpStatus op = compFacade.addCompany(dataComp);
		
		if ("OK".equals(op.getCod()))
			{
			return "companiesListView";
			}
		else
			{
			this.errMsg = op.getMsg();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.errMsg));
			return "addCompanyView";
			}
		}
	}
