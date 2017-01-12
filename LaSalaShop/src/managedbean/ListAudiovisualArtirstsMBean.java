package managedbean;

import java.util.*;

import javax.faces.bean.*;

import jpa.ArtistPrototypeJPA;



/**
 * Managed Bean ListCategoriesMBean
 */
@ManagedBean(name = "listAudiovisualArtistsMBean")
@SessionScoped

public class ListAudiovisualArtirstsMBean {
	

	protected Collection<ArtistPrototypeJPA> artistsListView;
	
	public ListAudiovisualArtirstsMBean() throws Exception{
		//this.bookList();
	}


	
	public Collection<ArtistPrototypeJPA> getArtistsListView() throws Exception	{		

		
		//CODIGO PARA EL PROTOTIPO:
		Collection<ArtistPrototypeJPA> ArtistsListView = new ArrayList<ArtistPrototypeJPA>();
		
		ArtistPrototypeJPA a1 = new ArtistPrototypeJPA("Albert", "Gonzalez", "98784563541");
		ArtistPrototypeJPA a2 = new ArtistPrototypeJPA("John", "Smith", "83-123456789");
		ArtistPrototypeJPA a3 = new ArtistPrototypeJPA("Tino", "Gonzalez", "9878-4563541");
		ArtistPrototypeJPA a4 = new ArtistPrototypeJPA("Susana", "Ninez", "9878456-3541");
		ArtistPrototypeJPA a5 = new ArtistPrototypeJPA("Maria", "Garcia", "98-7-84-56 35-41");
		ArtistPrototypeJPA a6 = new ArtistPrototypeJPA("John", "Gonzalez", "98784563541");
		ArtistPrototypeJPA a7 = new ArtistPrototypeJPA("Nombremuylargo", "Apellido-muy-largo", "98784563541");
		ArtistPrototypeJPA a8 = new ArtistPrototypeJPA("Pepe", "Perez", "98745663541");
		ArtistPrototypeJPA a9 = new ArtistPrototypeJPA("Albert", "Lee", "989-756-73541");
		ArtistPrototypeJPA a10 = new ArtistPrototypeJPA("Mike", "Doe", "98784654541");
		
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
