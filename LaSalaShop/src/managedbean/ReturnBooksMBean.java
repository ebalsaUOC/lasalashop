package managedbean;

import java.io.Serializable;
import java.util.*;

import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import javax.faces.bean.*;
import javax.faces.event.ValueChangeEvent;
import javax.naming.Context;
import javax.naming.InitialContext;

import jpa.BookJPA;
import jpa.CategoryJPA;
import ejb.CategoryFacadeRemote;



/**
 * Managed Bean ListCategoriesMBean
 */
@ManagedBean(name = "returnBooksMBean")
@SessionScoped

public class ReturnBooksMBean {
	
	//@EJB private CategoryFacadeRemote categories;
	protected Collection<SelectItem> booksList = new ArrayList<SelectItem>();
	protected Collection<BookJPA> booksListView;
	
	public ReturnBooksMBean() throws Exception
	{
		this.bookList();
	}

	/**
	 * Method get which return Categories Collection
	 * @return Collection
	 */
	public Collection<SelectItem> getBooksList()
	{
		return booksList;
	}
	
	/**
	 * Method that takes a collection of instances of CategoryJPA calling 
	 * method listAllCategories of the EJB Session
	 * @throws Exception
	 */
	public void bookList() throws Exception
	{	
//		Properties props = System.getProperties();
//		Context ctx = new InitialContext(props);
//		categories = (CategoryFacadeRemote) ctx.lookup("java:app/EPCSD_Practica.jar/CategoryFacadeBean!ejb.CategoryFacadeRemote");
//		@SuppressWarnings("unchecked")
//		Collection<CategoryJPA> categoryCollection = (Collection<CategoryJPA>) categories.listAllCategories();
//		for (Iterator<CategoryJPA> iter2 = categoryCollection.iterator(); iter2.hasNext();)
//		{
//			CategoryJPA cat = (CategoryJPA) iter2.next();
//			SelectItem item = new SelectItem(cat.getName());
//			this.categoryList.add(item);			
//		}PARA LA VERSION DEFINITIVA, OBTENER AQUI AMBAS LISTAS CON UNA LLAMADA AL EJB Y ELIMINAR LA LLAMADA EN EL METODO INFERIOR
		
		//CODIGO PARA EL PROTOTIPO:
		BookJPA b1 = new BookJPA();
		b1.setTitle("El Libro de la vida");
		BookJPA b2 = new BookJPA();
		b1.setTitle("El Libro de la muerte");
		BookJPA b3 = new BookJPA();
		b1.setTitle("El Libro con el titulo mas largo del mundo");
		BookJPA b4 = new BookJPA();
		b1.setTitle("I");
		BookJPA b5 = new BookJPA();
		b1.setTitle("5añ_!");
		booksListView.add(b1);
		booksListView.add(b2);
		booksListView.add(b3);
		booksListView.add(b4);
		booksListView.add(b5);
		
		for(BookJPA b: booksListView){
			SelectItem item = new SelectItem(b.getTitle());
			this.booksList.add(item);
		}
		
	
	}	
	
	public Collection<BookJPA> getBooksListView() throws Exception
	{		
//		Properties props = System.getProperties();
//		Context ctx = new InitialContext(props);
//		categories = (CategoryFacadeRemote) ctx.lookup("java:app/EPCSD_Practica.jar/CategoryFacadeBean!ejb.CategoryFacadeRemote");
//		categoryListView = (Collection<CategoryJPA>)categories.listAllCategories();
//		
//		return categoryListView;	
		
		//CODIGO PARA EL PROTOTIPO:
		BookJPA b1 = new BookJPA();
		b1.setTitle("El Libro de la vida");
		BookJPA b2 = new BookJPA();
		b1.setTitle("El Libro de la muerte");
		BookJPA b3 = new BookJPA();
		b1.setTitle("El Libro con el titulo mas largo del mundo");
		BookJPA b4 = new BookJPA();
		b1.setTitle("I");
		BookJPA b5 = new BookJPA();
		b1.setTitle("5añ_!");
		booksListView.add(b1);
		booksListView.add(b2);
		booksListView.add(b3);
		booksListView.add(b4);
		booksListView.add(b5);
		
		return booksListView;
		
	}
}
