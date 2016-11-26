package ejbEx;

import java.util.Collection;
import java.util.Set;

import javax.ejb.Remote;

import jpaEx.AddressJPA;
import jpaEx.CategoryJPA;
import jpaEx.CompanyJPA;
import jpaEx.EventJPA;
import jpaEx.LanguageJPA;
import jpaEx.UserJPA;
import jpaEx.WordJPA;

/**
 * Session EJB Remote Interfaces
 */
@Remote
public interface AdministrationFacadeRemote
	{
	/**
	 * Remotely invoked method.
	 */
	public OpStatus addEvent(EventJPA event);

	public OpStatus addCategoryToEvent(EventJPA event, CategoryJPA category);

	public OpStatus updateEvent(EventJPA event);

	public OpStatus register(UserJPA user);

	public OpStatus login(String email, String pwd);

	// Aux method to get all languages and display list on view
	public Collection<LanguageJPA> listLanguages();
	
	public Set<CompanyJPA> listCompanies();

	public OpStatus addAddress(AddressJPA address);

	// Aux method to set initial values for the app
	public void iniApp();

	// Metodo con el que podemsos listar las categorias asociadas a un evento
	public Collection<CategoryJPA> listCategories();

	// Al crear un nuevo evento necesitamos todas las palabras claves existentes
	public Set<WordJPA> getAllWords();

	public Object getWordById(Long valueOf);
	
	public Object getCategoryById(Long valueOf);
	
	public Object getCompanyById(Long valueOf);
	
	public UserJPA getUserForSession(String email);
	}
