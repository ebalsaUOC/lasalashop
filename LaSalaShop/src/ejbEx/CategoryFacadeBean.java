/*
 * 
 * @author cdominguez, 2014
 * 
 */

package ejbEx;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jpaEx.CategoryJPA;



/**
 * Create cdominguez
 * EJB Session Bean Class 
 */
@Stateless
public class CategoryFacadeBean implements CategoryFacadeRemote {
	
	// Persistence Unit Context
		@PersistenceContext(unitName = "eagenda")
		private EntityManager entman;
		

		public OpStatus addCategory(CategoryJPA category){
			
			OpStatus op = new OpStatus();
			
			try{
				Query query = entman
						.createNativeQuery("SELECT COUNT(c) " + "FROM eagenda.category AS c" + " WHERE c.name ='" + category.getName() + "'" );
				int count = ((Number) query.getSingleResult()).intValue();
				if (count == 1)
					{
					op.setCod("KO");
					op.setMsg("Nom en us. trieu un altre si us plau");
					return op;
					}
				entman.persist(category);
				op.setCod("OK");
				return op;
			
			} catch(Exception e){
				op.setCod("KO");
				op.setMsg( e.getMessage() );
				return op;
			}
		}


	public OpStatus updateCategory(int id, String name, String description) {

		OpStatus op = new OpStatus();
		CategoryJPA cat = entman.find(CategoryJPA.class, id);
		
		if(cat == null){
            op.setCod("KO: Unknown category id");
			return op;
		} else {
			try{
				cat.setName(name);
				cat.setDescription(description);
				entman.merge(cat);
				op.setCod("OK");
				return op;
			
			} catch(Exception e){
				op.setCod("KO");
				op.setMsg( e.getMessage() );
				return op;
			}
		}
			
	}
	
	/**
	 * Method that returns Collection of all categories
	 */
	@Override
	public List<CategoryJPA> listAllCategories() {
		@SuppressWarnings("unchecked")
		List<CategoryJPA> allCategories = entman.createQuery("from CategoryJPA order by category_id").getResultList();
		
		return allCategories;
	}

	@Override
	public CategoryJPA showCategory(int categoryId) {
		//@SuppressWarnings("unchecked")
		CategoryJPA cat = entman.find(CategoryJPA.class, categoryId);		
		return cat;
	}
	
		public void addCategory(String name, String description) 
		{
			CategoryJPA cat = new CategoryJPA();
			cat.setName(name);
			cat.setDescription(description);
			entman.persist(cat);		
		}
	


}
