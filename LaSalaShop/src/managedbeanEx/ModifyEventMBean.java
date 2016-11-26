package managedbeanEx;

import java.io.Serializable;
import java.util.*;

import javax.ejb.EJB;
import javax.faces.bean.*;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ejbEx.AdministrationFacadeRemote;
import ejbEx.OpStatus;
import jpaEx.AddressJPA;
import jpaEx.CommentJPA;
import jpaEx.CompanyJPA;
import jpaEx.EventJPA;
import jpaEx.RattingJPA;
import jpaEx.WordJPA;

/**
 * Managed Bean ModifyCompanyMBean
 */
@ManagedBean(name = "modifyEvent")
@SessionScoped
public class ModifyEventMBean implements Serializable
	{
	private static final long serialVersionUID = 1L;
	protected EventJPA dataEvent;
	@EJB
	private AdministrationFacadeRemote modifyEvent;
	private String errMsg;
	// Mapear pares de valores, en este caso id y tag
	private Map<String, WordJPA> allWordsList;
	private Set<WordJPA> selectedWords;
	private Set<WordJPA> allWords;
	
	protected int selectedCompany;
	protected Set<CompanyJPA> companies;
	protected Set<SelectItem> companiesList;

	public ModifyEventMBean() throws Exception
		{
		// Quitar cuando este implementada la sesion!
		this.iniApp();
		this.setAllWordsList(new LinkedHashMap<String, WordJPA>());
		this.allWordsList();
		this.setCompaniesList(new HashSet<SelectItem>());
		this.companiesList();
		}

	public void allWordsList() throws NamingException
		{
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		modifyEvent = (AdministrationFacadeRemote) ctx.lookup("java:app/e-agenda.jar/AdministrationFacadeBean!ejb.AdministrationFacadeRemote");
		this.setAllWords(modifyEvent.getAllWords());
		if (this.getAllWords() != null && this.getAllWords().size() > 0)
			{
			for (WordJPA word : this.getAllWords())
				{
				this.getAllWordsList().put(word.getTag(), word);
				}
			}
		}

	@SuppressWarnings("unchecked")
	public void wordsValueChanged(ValueChangeEvent wordsChanged)
		{
		this.setSelectedWords((Set<WordJPA>) wordsChanged.getNewValue());
		}

	public Set<WordJPA> getSelectedWords()
		{
		return selectedWords;
		}

	public void setSelectedWords(Set<WordJPA> selectedWords)
		{
		this.selectedWords = selectedWords;
		}

	public Set<WordJPA> getAllWords()
		{
		return allWords;
		}

	public void setAllWords(Set<WordJPA> allWords)
		{
		this.allWords = allWords;
		}

	public Map<String, WordJPA> getAllWordsList()
		{
		return allWordsList;
		}

	private void setAllWordsList(Map<String, WordJPA> allWordsList)
		{
		this.allWordsList = allWordsList;
		}

	// Aux method to set initial values for the app
	public void iniApp() throws NamingException
		{
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		modifyEvent = (AdministrationFacadeRemote) ctx.lookup("java:app/e-agenda.jar/AdministrationFacadeBean!ejb.AdministrationFacadeRemote");
		modifyEvent.iniApp();
		}

	public void edit(EventJPA event)
		{
		this.dataEvent = new EventJPA();
		this.dataEvent.setId(event.getId());
		this.dataEvent.setName(event.getName());
		this.dataEvent.setDescription(event.getDescription());
		this.dataEvent.setPicture(event.getPicture());
		this.dataEvent.setIniDate(event.getIniDate());
		this.dataEvent.setEndDate(event.getEndDate());
		this.dataEvent.setLocation(event.getLocation());
		this.dataEvent.setWords(event.getWords());
		this.dataEvent.setCategories(event.getCategories());
		this.dataEvent.setCompany(event.getCompany());
		
		if(event.getCompany()!=null) this.selectedCompany = dataEvent.getCompany().getId();
		}

	public Collection<CommentJPA> getComments()
		{
		return this.dataEvent.getComments();
		}

	public EventJPA getDataEvent()
		{
		return this.dataEvent;
		}

	public String getDescription()
		{
		return this.dataEvent.getDescription();
		}

	public Date getEndDate()
		{
		return this.dataEvent.getEndDate();
		}

	public int getId()
		{
		return this.dataEvent.getId();
		}

	public Date getIniDate()
		{
		return this.dataEvent.getIniDate();
		}

	public AddressJPA getLocation()
		{
		return this.dataEvent.getLocation();
		}

	public String getName()
		{
		return this.dataEvent.getName();
		}

	public String getPicture()
		{
		return this.dataEvent.getPicture();
		}

	public float getRatting()
		{
		return this.dataEvent.getRatting();
		}

	public Collection<RattingJPA> getRattings()
		{
		return this.dataEvent.getRattings();
		}

	public Collection<WordJPA> getWords()
		{
		return this.dataEvent.getWords();
		}

	public String modifyDataEvent() throws Exception
		{
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		modifyEvent = (AdministrationFacadeRemote) ctx.lookup("java:app/e-agenda.jar/AdministrationFacadeBean!ejb.AdministrationFacadeRemote");
		// Que no nos falten las keywords!
		dataEvent.setWords(this.getSelectedWords());
		OpStatus op = modifyEvent.updateEvent(dataEvent);
		if ("OK".equals(op.getCod()))
			{
			return "eventsListView"; // Siempre retornaremos a la vista de administracion
			}
		else
			{
			this.setErrMsg(op.getMsg());
			return "errorView";
			}
		}

	public void setComments(Set<CommentJPA> comments)
		{
		this.dataEvent.setComments(comments);
		}

	public void setDescription(String description)
		{
		this.dataEvent.setDescription(description);
		}

	public void setEndDate(Date endDate)
		{
		this.dataEvent.setEndDate(endDate);
		}

	public void setId(int id)
		{
		this.dataEvent.setId(id);
		}

	public void setIniDate(Date iniDate)
		{
		this.dataEvent.setIniDate(iniDate);
		}

	public void setLocation(AddressJPA location)
		{
		this.dataEvent.setLocation(location);
		}

	public void setName(String name)
		{
		this.dataEvent.setName(name);
		}

	public void setPicture(String picture)
		{
		this.dataEvent.setPicture(picture);
		}

	public void setRatting(float ratting)
		{
		this.dataEvent.setRatting(ratting);
		}

	public void setRattings(Set<RattingJPA> rattings)
		{
		this.dataEvent.setRattings(rattings);
		}

	public void setWords(Set<WordJPA> words)
		{
		this.dataEvent.setWords(words);
		}

	public String getErrMsg()
		{
		return errMsg;
		}

	public void setErrMsg(String errMsg)
		{
		this.errMsg = errMsg;
		}

	public String getStreet()
		{
		return dataEvent.getLocation().getStreet();
		}

	public void setStreet(String street)
		{
		this.dataEvent.getLocation().setStreet(street);
		}

	public String getCity()
		{
		return dataEvent.getLocation().getCity();
		}

	public void setCity(String city)
		{
		this.dataEvent.getLocation().setCity(city);
		}

	public String getCountry()
		{
		return dataEvent.getLocation().getCountry();
		}

	public void setCountry(String country)
		{
		this.dataEvent.getLocation().setCountry(country);
		}

	public String getNumber()
		{
		return dataEvent.getLocation().getNumber();
		}

	public void setNumber(String number)
		{
		this.dataEvent.getLocation().setNumber(number);
		}
	
	public void companiesList() throws NamingException
		{
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		modifyEvent = (AdministrationFacadeRemote) ctx.lookup("java:app/e-agenda.jar/AdministrationFacadeBean!ejb.AdministrationFacadeRemote");
		this.setCompanies(modifyEvent.listCompanies());
		if (this.getCompanies() != null && this.getCompanies().size() > 0)
			{
			for (CompanyJPA comp : this.getCompanies())
				{
				this.getCompaniesList().add( new SelectItem(comp.getId(), comp.getName()) );
				}
			}
		}
	
	public void setCompanies(Set<CompanyJPA> companies) 
		{
		this.companies = companies;
		}
	
	public Set<CompanyJPA> getCompanies() 
		{
		return this.companies;
		}
	
	public void setCompaniesList(Set<SelectItem> companiesList) 
		{
		this.companiesList = companiesList;	
		}
	
	public Set<SelectItem> getCompaniesList() 
		{
		return this.companiesList;	
		}
	
	public void companyValueChanged(ValueChangeEvent companyChanged)
		{
		CompanyJPA comp = (CompanyJPA) modifyEvent.getCompanyById( (long)((int) companyChanged.getNewValue()) );
		this.setCompany(comp);
		}
	
	public CompanyJPA getCompany()
		{
		return this.dataEvent.getCompany();
		}
	
	public void setCompany(CompanyJPA company)
		{
		this.dataEvent.setCompany(company);
		}
	
	public int getSelectedCompany()
		{
		return selectedCompany;
		}
	
	public void setSelectedCompany(int selectedCompany)
		{
		this.selectedCompany = selectedCompany;
		}


	
	}