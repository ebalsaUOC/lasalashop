/*
 * @author Alex Cabrera Gil - acabreragil@uoc.edu
 */
package ejbEx;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jpaEx.CompanyJPA;



@Stateless
public class CompanyFacadeBean implements CompanyFacadeRemote
	{
	
	// Persistence Unit Context
	@PersistenceContext(unitName = "eagenda")
	private EntityManager entman;
	
	/**
	 * Afegeix una empresa al sistema
	 * @param company referencia de les dades de la empresa a introduir
	 * @return un missatge amb el resultat de la operació
	 */
	@Override
	public OpStatus addCompany(CompanyJPA company)
		{
		
		OpStatus op = new OpStatus();
		
		try
			{
			Query query = entman
					.createNativeQuery("SELECT COUNT(c) " + "FROM eagenda.company AS c" + " WHERE c.name ='" + company.getName() + "'");
			int count = ((Number) query.getSingleResult()).intValue();
			if (count == 1)
				{
				op.setCod("KO");
				op.setMsg("Nom en us. trie un altre si us plau");
				return op;
				}
			entman.persist(company);
			op.setCod("OK");
			return op;
		
			} 
		catch(Exception e)
			{
			op.setCod("KO");
			op.setMsg( e.getMessage() );
			return op;
			}
		}
	
	
	/**
	 * Modifica una empresa al sistema
	 * @param id identificador de la empresa a modificar
	 * @param nou nom de la empresa que es vol modificar
	 * @return un missatge amb el resultat de la operació
	 */
	@Override
	public OpStatus updateCompany(int id, String name)
		{
		
		OpStatus op = new OpStatus();
		CompanyJPA company = entman.find(CompanyJPA.class, id);
		
		if(company == null)
			{
            op.setCod("KO: Unknown Company id");
			return op;
			} 
		else 
			{
			try
				{
				company.setName(name);
				entman.merge(company);
				op.setCod("OK");
				return op;
				} 
			catch(Exception e)
				{
				op.setCod("KO");
				op.setMsg( e.getMessage() );
				return op;
				}
			}
		}

	
	/**
	 * Mostra una empresa del sistema
	 * @param id identificador de la empresa que es vol mostrar
	 * @return una referència amb les dades de la empresa que s'ha demanat
	 */
	@Override
	public CompanyJPA showCompany(int id)
		{
		CompanyJPA company = entman.find(CompanyJPA.class, id);
		return company;
		}

	
	/**
	 * Llista totes les empreses del sistema
	 * @return una llista amb totes les empreses del sistema
	 */
	@Override
	public List<CompanyJPA> listAllCompanies() 
		{
		@SuppressWarnings("unchecked")
		List<CompanyJPA> allCompanies = entman.createQuery("from CompanyJPA order by COMPANY_ID").getResultList();
		
		return allCompanies;
		}
	}	
