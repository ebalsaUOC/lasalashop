package ejb;

import java.util.Collection;

import javax.ejb.Remote;

import jpa.CategoryJPA;
import jpa.DistribuidoraJPA;

/**
*
* @author ebalsa@uoc.edu
*/
@Remote
public interface LibroFacadeRemote {
	  /**
	   * Remotely invoked method.
	   */
	  	public void addBook(String titulo, String autor, String editorial, String isbn, 
	  			double netoCompra, double pvp, DistribuidoraJPA distribuidora);
//		public void updateCategory(int id, String name, String description);
//		public Collection<?> listAllCategories();
//		public CategoryJPA showCategory(int categoryId);
}
