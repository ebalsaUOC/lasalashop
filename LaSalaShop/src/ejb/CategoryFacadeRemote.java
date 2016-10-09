package ejb;

import java.util.Collection;
import javax.ejb.Remote;
import jpa.CategoryJPA;

/**
 * Session EJB Remote Interfaces
 */
@Remote
public interface CategoryFacadeRemote {
	  /**
	   * Remotely invoked method.
	   */
	  	public void addCategory(String name, String description);
		public void updateCategory(int id, String name, String description);
		public Collection<?> listAllCategories();
		public CategoryJPA showCategory(int categoryId);
}
