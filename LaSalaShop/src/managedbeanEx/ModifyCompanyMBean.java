/*
 * @author Alex Cabrera Gil - acabreragil@uoc.edu
 */
package managedbeanEx;

import java.io.Serializable;
import java.util.*;

import javax.ejb.EJB;
import javax.faces.bean.*;
import javax.naming.Context;
import javax.naming.InitialContext;

import ejbEx.CompanyFacadeRemote;
import ejbEx.OpStatus;
import jpaEx.CompanyJPA;

/**
 * Managed Bean ModifyCompanyMBean
 */
@ManagedBean(name = "modifyCompany")
@SessionScoped
public class ModifyCompanyMBean implements Serializable
	{
	
	private static final long serialVersionUID = 1L;

	@EJB
	private CompanyFacadeRemote modifyCompany;
	protected CompanyJPA dataCompany;
	protected Integer id;
	protected String name;
	protected String errMsg;
	
	public ModifyCompanyMBean() throws Exception 
		{
		
		}
	
	/*
	 * Getters / Setters
	 */
	public int getId()
		{
		return dataCompany.getId();
		}
	public void setId(int id)
		{
		dataCompany.setId(id);	
		}
	
	public String getName()
		{
		return dataCompany.getName();
		}
	public void setName(String name)
		{
		dataCompany.setName(name);	
		}
	
	public CompanyJPA getDataCompany()
		{
		return dataCompany;
		}
	
	/**
	 * inicialitza les dades de la pantalla
	 * @param comp referencia a les dades a utilitzar
	 */
	public void edit (CompanyJPA comp) 
		{
		dataCompany = new CompanyJPA();
		dataCompany.setId(comp.getId());
		dataCompany.setName(comp.getName());
		}
	
		
	/**
	 * Modifica una empresa al sistema
	 * @return la pantalla de destí corresponent
	 */
	public String modifyDataCompany() throws Exception
		{	
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		modifyCompany = (CompanyFacadeRemote) ctx.lookup("java:app/e-agenda.jar/CompanyFacadeBean!ejb.CompanyFacadeRemote");
	
		OpStatus op = modifyCompany.updateCompany(dataCompany.getId(), dataCompany.getName());
		
		if("OK".equals(op.getCod())) 
			return "companiesListView";
		else
			{
			this.errMsg = op.getMsg();
			return "errorView";
			}
		}
	}
