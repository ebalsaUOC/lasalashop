/*
 * @author Alex Cabrera Gil - acabreragil@uoc.edu
 */
package ejbEx;

import java.util.List;

import javax.ejb.Remote;

import jpaEx.CompanyJPA;

/**
 * Session EJB Remote Interfaces
 */
@Remote
public interface CompanyFacadeRemote
	{
	/**
	 * Remotely invoked method.
	 */
	public OpStatus addCompany(CompanyJPA company);

	public OpStatus updateCompany(int id, String name);

	public CompanyJPA showCompany(int id);

	public List<CompanyJPA> listAllCompanies();
	
	
	}
