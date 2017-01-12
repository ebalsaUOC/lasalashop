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
import javax.naming.Context;
import javax.naming.InitialContext;
import ejb.BookManagementFacade;


/**
 * Managed Bean LoginMBean
 */
@ManagedBean(name = "searchBookMBean")
@SessionScoped
public class SearchBookMBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@EJB
	private BookManagementFacade bookRemote;
	
	public SearchBookMBean() throws Exception{
	
	}

	public String search(String name, String author, String isbn) throws Exception{
		//lookup for business class
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		bookRemote = (BookManagementFacade)ctx.lookup("java:app/lasalashop.jar/BookManagementImpl!ejb.BookManagementFacade"); 
		
		List<String> filters = new ArrayList<String>();
		String searchText="";
		
		//Select search criteria:
		if(name!=null && !"".equalsIgnoreCase(name.trim())){
			filters.add("NAME");
			searchText=name;
		} else if (author!=null && !"".equalsIgnoreCase(author.trim())){
			filters.add("AUTHOR");
			searchText=author;
		} else {
			filters.add("ISBN");
			searchText=isbn;
		}
	
		
		
		return "ListBooksResultView";
	}

}
