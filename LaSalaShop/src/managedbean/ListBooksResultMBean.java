package managedbean;

import java.util.*;

import javax.faces.model.SelectItem;
import javax.faces.bean.*;

import jpa.BookJPA;



/**
 * Managed Bean ListCategoriesMBean
 */
@ManagedBean(name = "listBooksResultMBean")
@SessionScoped

public class ListBooksResultMBean {
	
	//@EJB private CategoryFacadeRemote categories;
//	protected Collection<SelectItem> booksList = new ArrayList<SelectItem>();
	protected Collection<BookJPA> booksListView;
	
	public ListBooksResultMBean() throws Exception
	{
		//this.bookList();
	}

	/**
	 * Method get which return Categories Collection
	 * @return Collection
	 */
//	public Collection<SelectItem> getBooksList()
//	{
//		return booksList;
//	}
	
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
//		BookJPA b1 = new BookJPA();
//		b1.setTitle("El Libro de la vida");
//		BookJPA b2 = new BookJPA();
//		b2.setTitle("El Libro de la muerte");
//		BookJPA b3 = new BookJPA();
//		b3.setTitle("El Libro con el titulo mas largo del mundo");
//		BookJPA b4 = new BookJPA();
//		b4.setTitle("I");
//		BookJPA b5 = new BookJPA();
//		b5.setTitle("5añ_!");
//		this.booksListView.add(b1);
////		this.booksListView.add(b2);
//		this.booksListView.add(b3);
//		this.booksListView.add(b4);
//		this.booksListView.add(b5);
//		
////		for(BookJPA b: booksListView){
//			SelectItem item = new SelectItem("TITULO");
//			this.booksList.add(item);
////		}
//		
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
		Collection<BookJPA> booksListViewP = new ArrayList<BookJPA>();
		BookJPA b1 = new BookJPA();
		b1.setTitle("El Libro de la vida");
		b1.setPvp(19.99);
		BookJPA b2 = new BookJPA();
		b2.setTitle("El Libro de la muerte");
		b2.setPvp(11.99);
		BookJPA b3 = new BookJPA();
		b3.setTitle("El Libro con el titulo mas largo del mundo");
		b3.setPvp(9.00);
		booksListViewP.add(b1);
		booksListViewP.add(b2);
		booksListViewP.add(b3);

			
		return booksListViewP;
		
	}
}
