/*
 * @author Alex Cabrera Gil - acabreragil@uoc.edu
 */
package managedbeanEx;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import jpaEx.CompanyJPA;
import ejbEx.CompanyFacadeRemote;


/**
 * Managed Bean ShowCompanyMBean
 */
@ManagedBean(name = "showCompany")
@SessionScoped
public class ShowCompanyMBean implements Serializable
	{
	
	private static final long serialVersionUID = 1L;

	@EJB
	private CompanyFacadeRemote showCompany;
	protected CompanyJPA dataCompany = new CompanyJPA();
	
	
	public ShowCompanyMBean() throws Exception	
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
	 * Mostra una empresa del sistema
	 * @param comp referència de la empresa que es vol mostrar
	 */
	public void show (CompanyJPA comp) 
		{
		dataCompany.setId(comp.getId());
		dataCompany.setName(comp.getName());
		}

	}