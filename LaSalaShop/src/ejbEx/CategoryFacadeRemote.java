
package ejbEx;

import java.util.Collection;
import java.util.List;

import javax.ejb.Remote;

import jpaEx.CategoryJPA;

/**
 * create cdominguez
 * Session EJB Remote Interfaces
 */
@Remote
public interface CategoryFacadeRemote
	{
	/**
	 * Remotely invoked method.
	 */
	public void addCategory(String name, String description);
	public OpStatus updateCategory(int id, String name, String description);
	public List<CategoryJPA> listAllCategories();
	public CategoryJPA showCategory(int categoryId);
	public OpStatus addCategory(CategoryJPA category);
}
	
	
