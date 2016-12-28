/*
 * 
 * @author ebalsa@uoc.edu
 */
package managedbean;

import java.io.Serializable;
import java.util.*;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import domain.OpStatus;
import jpa.BookJPA;
import jpa.DistributorJPA;
import ejb.BookManagementFacade;


/**
 * Managed Bean LoginMBean
 */
@ManagedBean(name = "bookEditMBean")
@SessionScoped
public class BookEditMBean implements Serializable
	{
	private static final long serialVersionUID = 1L;
	private BookJPA dataBook;
	@EJB
	private BookManagementFacade bookRemote;
	private String errMsg;
	private String okMsg;
	
	public BookEditMBean() throws Exception{
	
	}

	public void showBook(BookJPA b) throws Exception	{	
		this.dataBook=b;
	}
	
	public String editBook(String text) throws Exception{
		//lookup for business class
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		bookRemote = (BookManagementFacade)ctx.lookup("java:app/lasalashop.jar/BookManagementImpl!ejb.BookManagementFacade"); 

//TODO: RECOGER VALORES Y EDITAR		
		
		//Instantiate the return object
		OpStatus op = new OpStatus();
		
		//Call Business to edit Book
//TODO: DESCOMENTAR		op = bookRemote.editBookB(b);
						
		//Parse return from business layer
		if("OK".equals(op.getCod())){
			this.okMsg = op.getMsg();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.okMsg));
			//Redirect to main
			return "lasalashopMainTemplate";
		}
		
		else{
			this.errMsg = op.getMsg();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.errMsg));
			return "addBookView";
		}
	}

	public BookJPA getDataBook() {
		return dataBook;
	}

	public void setDataBook(BookJPA dataBook) {
		this.dataBook = dataBook;
	}

	public BookManagementFacade getBookRemote() {
		return bookRemote;
	}

	public void setBookRemote(BookManagementFacade bookRemote) {
		this.bookRemote = bookRemote;
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
}
