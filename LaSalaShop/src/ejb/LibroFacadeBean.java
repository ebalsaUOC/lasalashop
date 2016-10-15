package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import domain.EnumeratedStatus;
import jpa.DistributorJPA;
import jpa.BookJPA;

/**
 * EJB Session Bean Class 
 */
@Stateless
public class LibroFacadeBean implements LibroFacadeRemote {
	
	
	public LibroFacadeBean() {
		super();
	}

	//Persistence Unit Context
	@PersistenceContext(unitName="lasalashop") 
	private EntityManager entman;
	
	/**
	 * Method that add new Book
	 */	
	@Override
	public void addBook(String title, String author, String editor,
			String isbn, double price, double pvp,
			DistributorJPA distributor) {
		BookJPA book = new BookJPA();
		book.setTitle(title);
		book.setAuthor(author);
		book.setEditor(editor);
		book.setIsbn(isbn);
		book.setPrice(price);
		book.setPvp(pvp);
		book.setDistributor(distributor);
		book.setStatus(EnumeratedStatus.AVAILABLE);
		entman.persist(book);
	}

}
