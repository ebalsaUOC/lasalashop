/*
 * 
 * @author ebalsa@uoc.edu
 */
package managedbean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import jpa.BookJPA;


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
