package managedbean;

import java.util.*;

import javax.faces.model.SelectItem;
import javax.faces.bean.*;

import jpa.ArtistJPA;
import jpa.BookJPA;



/**
 * Managed Bean ListCategoriesMBean
 */
@ManagedBean(name = "listAudiovisualArtistsMBean")
@SessionScoped

public class ListAudiovisualArtirstsMBean {
	
	//@EJB private CategoryFacadeRemote categories;
//	protected Collection<SelectItem> booksList = new ArrayList<SelectItem>();
	protected Collection<ArtistJPA> artistsListView;
	
	public ListAudiovisualArtirstsMBean() throws Exception
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
	
	
	public Collection<ArtistJPA> getArtistsListView() throws Exception
	{		
//		Properties props = System.getProperties();
//		Context ctx = new InitialContext(props);
//		categories = (CategoryFacadeRemote) ctx.lookup("java:app/EPCSD_Practica.jar/CategoryFacadeBean!ejb.CategoryFacadeRemote");
//		categoryListView = (Collection<CategoryJPA>)categories.listAllCategories();
//		
//		return categoryListView;	
		
		//CODIGO PARA EL PROTOTIPO:
		Collection<ArtistJPA> ArtistsListView = new ArrayList<ArtistJPA>();
		
		ArtistJPA a1 = new ArtistJPA("Albert", "Gonzalez", "98784563541");
		ArtistJPA a2 = new ArtistJPA("John", "Smith", "83-123456789");
		ArtistJPA a3 = new ArtistJPA("Tino", "Gonzalez", "9878-4563541");
		ArtistJPA a4 = new ArtistJPA("Susana", "Ninez", "9878456-3541");
		ArtistJPA a5 = new ArtistJPA("Maria", "Garcia", "98-7-84-56 35-41");
		ArtistJPA a6 = new ArtistJPA("John", "Gonzalez", "98784563541");
		ArtistJPA a7 = new ArtistJPA("Nombremuylargo", "Apellido-muy-largo", "98784563541");
		ArtistJPA a8 = new ArtistJPA("Pepe", "Perez", "98745663541");
		ArtistJPA a9 = new ArtistJPA("Albert", "Lee", "989-756-73541");
		ArtistJPA a10 = new ArtistJPA("Mike", "Doe", "98784654541");
		
		ArtistsListView.add(a1);
		ArtistsListView.add(a2);
		ArtistsListView.add(a3);
		ArtistsListView.add(a4);
		ArtistsListView.add(a5);
		ArtistsListView.add(a6);
		ArtistsListView.add(a7);
		ArtistsListView.add(a8);
		ArtistsListView.add(a9);
		ArtistsListView.add(a10);
			
		return ArtistsListView;
		
	}
}
