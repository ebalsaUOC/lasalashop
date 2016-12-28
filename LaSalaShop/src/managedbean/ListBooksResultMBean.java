package managedbean;

import java.util.*;

import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import javax.faces.bean.*;
import javax.naming.Context;
import javax.naming.InitialContext;

import ejb.BookManagementFacade;
import jpa.BookJPA;



/**
 * Managed Bean ListCategoriesMBean
 */
@ManagedBean(name = "listBooksResultMBean")
@SessionScoped

public class ListBooksResultMBean {
	
	private static final long serialVersionUID = 1L;
	@EJB
	private BookManagementFacade bookRemote;
	protected Collection<SelectItem> booksList = new ArrayList<SelectItem>();
	protected Collection<BookJPA> booksListView;
	
	public ListBooksResultMBean() throws Exception{
	
	}

	//List all the books according to search criteria 
	public String bookSearch(String name, String author, String isbn) throws Exception	{	
		//lookup for business class
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		bookRemote = (BookManagementFacade)ctx.lookup("java:app/lasalashop.jar/BookManagementImpl!ejb.BookManagementFacade"); 
		
		List<String> filters = new ArrayList<String>();
		String searchText="";
		
		//Select search criteria:
		if(name!=null && !"".equalsIgnoreCase(name.trim())){
			filters.add("TITLE");
			searchText=name;
		} else if (author!=null && !"".equalsIgnoreCase(author.trim())){
			filters.add("AUTHOR");
			searchText=author;
		} else {
			filters.add("ISBN");
			searchText=isbn;
		}
				
		//Get Books from business layer
		this.booksListView = new ArrayList<BookJPA>();
		Collection<BookJPA> resultList= bookRemote.searchBookB(searchText, filters);
		if(resultList!=null){
			this.booksListView=resultList;
		}
	
		return "listBooksResultView";
	}

	public BookManagementFacade getBookRemote() {
		return bookRemote;
	}

	public void setBookRemote(BookManagementFacade bookRemote) {
		this.bookRemote = bookRemote;
	}

	public Collection<SelectItem> getBooksList() {
		return booksList;
	}

	public void setBooksList(Collection<SelectItem> booksList) {
		this.booksList = booksList;
	}

	public Collection<BookJPA> getBooksListView() {
		return booksListView;
	}

	public void setBooksListView(Collection<BookJPA> booksListView) {
		this.booksListView = booksListView;
	}	
	

}
