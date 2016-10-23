/*
 * 
 * @author ebalsa@uoc.edu
 */
package managedbean;

import java.io.Serializable;
import java.util.*;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.naming.NamingException;
import jpa.DistributorJPA;
import ejb.LibroFacadeRemote;


/**
 * Managed Bean LoginMBean
 */
@ManagedBean(name = "searchBookMBean")
@SessionScoped
public class SearchBookMBean implements Serializable
	{
	private static final long serialVersionUID = 1L;
//	@EJB
//	private LibroFacadeRemote bookRemote;
	
	public SearchBookMBean() throws Exception
		{
	
		}

	public String serach(String s) throws Exception{

		return "ListBooksResultView";
	}

}
