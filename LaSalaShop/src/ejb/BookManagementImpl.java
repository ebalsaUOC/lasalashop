package ejb;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
	public OpStatus addBookB(BookJPA b) {
		OpStatus status = new OpStatus();
		try{
			
			//Create one instance for each copy
			int units = b.getCopy();
			int copy = 1;
			while (copy<=units){
				BookJPA bookToPersists = new BookJPA();
				bookToPersists.setAuthor(b.getAuthor());
				bookToPersists.setEditor(b.getEditor());
				bookToPersists.setIsbn(b.getIsbn());
				bookToPersists.setPrice(b.getPrice());
				bookToPersists.setPvp(b.getPvp());
				bookToPersists.setStatus(EnumeratedStatus.AVAILABLE);
				bookToPersists.setCopy(copy);
				bookToPersists.setTitle(b.getTitle());
				bookToPersists.setDistributor(b.getDistributor());
				copy++;
				entman.persist(bookToPersists);
			}
		} catch(Exception e){
			status.setMsg("Database error creating Book :"+e);
			return status;
		}
		
		status.setCod("OK");
		status.setMsg("Book created sucessfull");
		return status;
			
	}

	
	/**
	 * List all Distributors stored 
	 */	
	@Override
	public Collection<DistributorJPA> listAllDistributorsB() {
		// JPQL Query to fetch all entities from distributors table
		Query query = entman.createQuery("SELECT d FROM DistributorJPA d" );
		Collection<DistributorJPA> distributorsList = query.getResultList();
		return distributorsList;
	}
	
	
	/**
	 *Search a book according to given filter\s
	 */	
	@Override
	public Collection<BookJPA> searchBookB(String searchText,
			List<String> filters) {
		
		String filter = filters.get(0);
		//Select query type:
		String sQuery = "";
		if("TITLE".equalsIgnoreCase(filter)){
			sQuery="SELECT b FROM BookJPA b WHERE LOWER(b.title) LIKE :par";
		} else if(("AUTHOR".equalsIgnoreCase(filter))){
			sQuery="SELECT b FROM BookJPA b WHERE LOWER(b.author) LIKE :par";				
		} else {
			sQuery="SELECT b FROM BookJPA b WHERE LOWER(b.isbn) LIKE :par";
		}
		
		//Set parameter and run query
		sQuery = sQuery.replace(":par", "'%"+searchText.toLowerCase().trim()+"%'");
		Query query = entman.createQuery(sQuery);	
		Collection<BookJPA> resultList=query.getResultList();

		return resultList;
	}
	

	
	/**
	 * Edit a Book
	 */		
	@Override
	public OpStatus editBookB(BookJPA b) {
		String sQuery="UPDATE BookJPA  SET title = :title, "
				+ " author = :author, "
				+ " editor = :editor, "
				+ " price = :price, "
				+ " pvp = :pvp, "
				+ "  WHERE b.isbn=";
		
		Query query = entman.createQuery(sQuery)
				.setParameter("author", b.getAuthor())
				.setParameter("editor", b.getEditor())
				.setParameter("price", b.getPrice())
				.setParameter("pvp", b.getPvp())
				.setParameter("title", b.getTitle());
		
		query.executeUpdate();
		
		OpStatus status = new OpStatus();
		status.setCod("OK");
		status.setMsg("Book created sucessfull");
		return status;
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
