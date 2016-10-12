package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import domain.EnumeratedStatus;
import jpa.DistribuidoraJPA;
import jpa.LibroJPA;

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
	public void addBook(String titulo, String autor, String editorial,
			String isbn, double netoCompra, double pvp,
			DistribuidoraJPA distribuidora) {
		LibroJPA lib = new LibroJPA();
		lib.setTitulo(titulo);
		lib.setAutor(autor);
		lib.setEditorial(editorial);
		lib.setIsbn(isbn);
		lib.setNetoCompra(netoCompra);
		lib.setPvp(pvp);
		lib.setDistribuidora(distribuidora);
		lib.setStatus(EnumeratedStatus.AVAILABLE);
		entman.persist(lib);
	}

}
