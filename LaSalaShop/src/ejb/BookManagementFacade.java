package ejb;

import java.util.Collection;
import java.util.List;

import javax.ejb.Remote;

import domain.OpStatus;
import jpa.BookJPA;
import jpa.CategoryJPA;
import jpa.DistributorJPA;

/**
 * Session EJB Remote Interfaces
 */
@Remote
public interface BookManagementFacade {

	//CU_L1: Add new book to System
	public OpStatus addBookB(BookJPA b);
	
	//AUX_M: List all Distributors stored 
	public Collection<DistributorJPA> listAllDistributorsB();
	
	//CU_L2: Search a book according to given filter\s
	public Collection<BookJPA> searchBookB(String searchText, List<String> filters); 
	
	//CU_L3: Edit a Book
	public String editBookB(BookJPA b);
	
	//AUX_M: Show book details for a given id
	public BookJPA showBookDetailsB(long idBook); 
	
	//CU_L4: Add new Distributor to System
	public OpStatus addDistributor(DistributorJPA d);
	
	//CU_L5: Edit a Distributor
	public String editDistributorB(DistributorJPA d);
	
	//AUX_M: Show distributor details for a given id
	public BookJPA showDistributorDetailsB(long idDistributor); 
	
	//CU_L7: Return Books to Distributor
	public String returnBooksB(List<BookJPA> returnBooksList);
	
	//AUX_M: Show all Books related to a given Distributor id
	public Collection<BookJPA> showAllBooksByDistributorB(int idDistributor);
	
}
