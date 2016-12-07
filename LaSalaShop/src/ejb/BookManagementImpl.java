package ejb;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import domain.EnumeratedStatus;
import domain.OpStatus;
import jpa.DistributorJPA;
import jpa.BookJPA;

/**
 * EJB Session Bean Class 
 */
@Stateless
public class BookManagementImpl implements BookManagementFacade {
	
	
	public BookManagementImpl() {
		super();
	}

	//Persistence Unit Context
	@PersistenceContext(unitName="lasalashop") 
	private EntityManager entman;


	/**
	 * Adds new Book to System
	 */	
	@Override
	public String addBookB(BookJPA b) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * List all Distributors stored 
	 */	
	@Override
	public Collection<DistributorJPA> listAllDistributorsB() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 *Search a book according to given filter\s
	 */	
	@Override
	public Collection<BookJPA> searchBookB(String searchText,
			List<String> filters) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * Edit a Book
	 */		
	@Override
	public String editBookB(BookJPA b) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * Show book details for a given id
	 */		
	@Override
	public BookJPA showBookDetailsB(long idBook) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Add new Distributor to System
	 */	
	@Override
	public OpStatus addDistributor(DistributorJPA d) {
		OpStatus status = new OpStatus();
		try{
			entman.persist(d);
			status.setCod("OK");
			status.setMsg("Distributor created sucessfull");
			return status;
		} catch(Exception e){
			status.setMsg("Database error creating Distributor :"+e);
			return status;
		}
			
	}
	
	
	/**
	 * Edit a Distributor
	 */	
	@Override
	public String editDistributorB(DistributorJPA d) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * Show distributor details for a given id
	 */	
	@Override
	public BookJPA showDistributorDetailsB(long idDistributor) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * Return Books to Distributor
	 */
	@Override
	public String returnBooksB(List<BookJPA> returnBooksList) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * Show all Books related to a given Distributor id
	 */
	@Override
	public Collection<BookJPA> showAllBooksByDistributorB(int idDistributor) {
		// TODO Auto-generated method stub
		return null;
	}


}
