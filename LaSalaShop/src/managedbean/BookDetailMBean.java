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
@ManagedBean(name = "bookDetailMBean")
@SessionScoped
public class BookDetailMBean implements Serializable
	{
	private static final long serialVersionUID = 1L;
	private BookJPA dataBook;
	
	public BookDetailMBean() throws Exception{
	
	}

	public void showBook(BookJPA b) throws Exception	{	
		this.dataBook=b;
	}

	public BookJPA getDataBook() {
		return dataBook;
	}

	public void setDataBook(BookJPA dataBook) {
		this.dataBook = dataBook;
	}
}
