package managedbean;

import java.util.*;

import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import javax.faces.bean.*;
import javax.faces.event.ValueChangeEvent;

import ejb.BookManagementFacade;
import jpa.BookJPA;
import jpa.DistributorJPA;



/**
 * Managed Bean ListCategoriesMBean
 */
@ManagedBean(name = "returnBooksMBean")
@SessionScoped

public class ReturnBooksMBean {
	
	private static final long serialVersionUID = 1L;
	@EJB
	private BookManagementFacade bookRemote;
	private Collection<BookJPA> booksListView;
	private String distributor;
	private Collection<DistributorJPA> distributors = new ArrayList<DistributorJPA>();
	private Collection<String> distributorsDescList = new ArrayList<String>();
	private String errMsg;
	private String okMsg;
	
	public ReturnBooksMBean() throws Exception{
		//Get Distributors description onLoad
		distributorsDescList();	
	}

	
	/**
	 * Method that takes a collection of instances of CategoryJPA calling 
	 * method listAllCategories of the EJB Session
	 * @throws Exception
	 */	
	
	public Collection<BookJPA> getBooksListView() throws Exception
	{		
//		Properties props = System.getProperties();
//		Context ctx = new InitialContext(props);
//		categories = (CategoryFacadeRemote) ctx.lookup("java:app/EPCSD_Practica.jar/CategoryFacadeBean!ejb.CategoryFacadeRemote");
//		categoryListView = (Collection<CategoryJPA>)categories.listAllCategories();
//		
//		return categoryListView;	
		
		//CODIGO PARA EL PROTOTIPO:
		Collection<BookJPA> booksListViewP = new ArrayList<BookJPA>();
		BookJPA b1 = new BookJPA();
		b1.setTitle("El Libro de la vida");
		BookJPA b2 = new BookJPA();
		b2.setTitle("El Libro de la muerte");
		BookJPA b3 = new BookJPA();
		b3.setTitle("El Libro con el titulo mas largo del mundo");
		BookJPA b4 = new BookJPA();
		b4.setTitle("I");
		BookJPA b5 = new BookJPA();
		b5.setTitle("5añ_!");
		booksListViewP.add(b1);
		booksListViewP.add(b2);
		booksListViewP.add(b3);
		booksListViewP.add(b4);
		booksListViewP.add(b5);
			
		return booksListViewP;
		
	}
	
	private void distributorsDescList(){
		
		//TODO: CODIFICAR EN ADDBOOK Y COPIAR
		
		this.distributorsDescList.add("DISTRIB_1");
		this.distributorsDescList.add("dist_2");
		this.distributorsDescList.add("La Otra S.A.");
		

	}
	
	public void distributorValueChanged(ValueChangeEvent distributorChanged) throws Exception{
		
		//if distributor is changed into view, books list is updated
		this.distributor = distributorChanged.getNewValue().toString();
		getBooksListView();
	}


	public BookManagementFacade getBookRemote() {
		return bookRemote;
	}


	public void setBookRemote(BookManagementFacade bookRemote) {
		this.bookRemote = bookRemote;
	}


	public String getDistributor() {
		return distributor;
	}


	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}


	public Collection<DistributorJPA> getDistributors() {
		return distributors;
	}


	public void setDistributors(Collection<DistributorJPA> distributors) {
		this.distributors = distributors;
	}


	public Collection<String> getDistributorsDescList() {
		return distributorsDescList;
	}


	public void setDistributorsDescList(Collection<String> distributorsDescList) {
		this.distributorsDescList = distributorsDescList;
	}


	public String getErrMsg() {
		return errMsg;
	}


	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}


	public String getOkMsg() {
		return okMsg;
	}


	public void setOkMsg(String okMsg) {
		this.okMsg = okMsg;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public void setBooksListView(Collection<BookJPA> booksListView) {
		this.booksListView = booksListView;
	}
}
