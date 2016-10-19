package managedbean;
///*
// * @author Xesco Alarcon Moral 
// */
//package managedbean;
//
//import java.io.Serializable;
//import java.text.SimpleDateFormat;
//import java.util.Collection;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.LinkedHashMap;
//import java.util.Map;
//import java.util.Properties;
//import java.util.Set;
//
//import javax.ejb.EJB;
//import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
//import javax.faces.context.FacesContext;
//import javax.faces.event.ValueChangeEvent;
//import javax.faces.model.SelectItem;
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//
//import jpa.AddressJPA;
//import jpa.CommentJPA;
//import jpa.CompanyJPA;
//import jpa.EventJPA;
//import jpa.RattingJPA;
//import jpa.WordJPA;
//import ejb.AdministrationFacadeRemote;
//import jpa.CategoryJPA;
//import ejb.OpStatus;
//
///**
// * Managed Bean AddCompanyMBean
// */
//@ManagedBean(name = "addEvent")
//@RequestScoped
//public class AddEventMBean implements Serializable
//	{
//	@EJB
//	private AdministrationFacadeRemote eventFacade;
//	private static final long serialVersionUID = 1L;
//	protected EventJPA dataEvent;
//	protected Integer id;
//	protected String name;
//	protected String description;
//	protected String picture;
//	private Set<WordJPA> selectedWords;
//	private Set<WordJPA> allWords;
//	protected float ratting;
//	protected Date iniDate;
//	protected Date endDate;
//	protected Set<CommentJPA> comments;
//	protected Set<RattingJPA> rattings;
//	protected SimpleDateFormat format = new SimpleDateFormat("YYYY MM DD");
//	protected String errMsg;
//	protected Set<CategoryJPA> categories;
//	protected CompanyJPA company;
//	protected int selectedCompany;
//	protected Set<CompanyJPA> companies;
//	protected Set<SelectItem> companiesList;
//	
//	private String street;
//	private String city;
//	private String country;
//	private String number;
//	protected AddressJPA location;
//	
//	// Mapear pares de valores, en este caso id y tag
//	private Map<String, WordJPA> allWordsList;
//	@EJB
//	private AdministrationFacadeRemote adminRemote;
//
//	public AddEventMBean() throws Exception
//		{
//		// Quitar cuando este implementada la sesion!
//		this.iniApp();
//		this.setAllWordsList(new LinkedHashMap<String, WordJPA>());
//		this.allWordsList();
//		this.setCompaniesList(new HashSet<SelectItem>());
//		this.companiesList();
//		}
//
//	public void allWordsList() throws NamingException
//		{
//		Properties props = System.getProperties();
//		Context ctx = new InitialContext(props);
//		eventFacade = (AdministrationFacadeRemote) ctx.lookup("java:app/e-agenda.jar/AdministrationFacadeBean!ejb.AdministrationFacadeRemote");
//		this.setAllWords(eventFacade.getAllWords());
//		if (this.getAllWords() != null && this.getAllWords().size() > 0)
//			{
//			for (WordJPA word : this.getAllWords())
//				{
//				this.getAllWordsList().put(word.getTag(), word);
//				}
//			}
//		}
//	
//	@SuppressWarnings("unchecked")
//	public void wordsValueChanged(ValueChangeEvent wordsChanged)
//		{
//		this.setSelectedWords((Set<WordJPA>) wordsChanged.getNewValue());
//		System.out.println("wordsValueChanged");
//		}
//
//	public void setId(Integer id)
//		{
//		this.id = id;
//		}
//
//	public Integer getId()
//		{
//		return this.id;
//		}
//
//	public void setName(String name)
//		{
//		this.name = name;
//		}
//
//	public String getName()
//		{
//		return this.name;
//		}
//
//	public String getDescription()
//		{
//		return description;
//		}
//
//	public void setDescription(String description)
//		{
//		this.description = description;
//		}
//
//	public String getPicture()
//		{
//		return picture;
//		}
//
//	public void setPicture(String picture)
//		{
//		this.picture = picture;
//		}
//
//	public float getRatting()
//		{
//		return ratting;
//		}
//
//	public void setRatting(float ratting)
//		{
//		this.ratting = ratting;
//		}
//
//	public AddressJPA getLocation()
//		{
//		return location;
//		}
//
//	public void setLocation(AddressJPA location)
//		{
//		this.location = location;
//		}
//
//	public Date getIniDate()
//		{
//		return iniDate;
//		}
//
//	public void setIniDate(Date iniDate)
//		{
//		this.iniDate = iniDate;
//		}
//
//	public Date getEndDate()
//		{
//		return endDate;
//		}
//
//	public void setEndDate(Date endDate)
//		{
//		this.endDate = endDate;
//		}
//
//	public SimpleDateFormat getFormat()
//		{
//		return format;
//		}
//
//	public void setFormat(SimpleDateFormat format)
//		{
//		this.format = format;
//		}
//
//	public Collection<RattingJPA> getRattings()
//		{
//		return rattings;
//		}
//
//	public void setRattings(Set<RattingJPA> rattings)
//		{
//		this.rattings = rattings;
//		}
//
//	public Collection<CommentJPA> getComments()
//		{
//		return comments;
//		}
//
//	public void setComments(Set<CommentJPA> comments)
//		{
//		this.comments = comments;
//		}
//
//	public String AddEvent() throws Exception
//		{
//		System.out.println("AddEvent");
//		
//		if(this.name==null || "".trim().equals(this.name.trim()) ||
//				this.description==null || "".equals(this.description.trim()) ||
//				this.iniDate==null || "".equals(this.iniDate) ||
//				this.endDate==null || "".equals(this.endDate) ||
//				this.picture==null || "".equals(this.picture) ||				
//				country==null || "".equals(country.trim()) ||
//				city==null || "".equals(city.trim()) ||
//				number==null || "".equals(number.trim()) ||
//				street==null || "".equals(street.trim()) ){
//			
//			this.errMsg = "Ompli tots els camps si us plau";
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.errMsg));
//			return "addEventView";
//			
//		}
//		if(endDate.before(iniDate)){
//			this.errMsg = "Data de fi te que ser mes gran que data d'inici";
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.errMsg));
//			return "addEventView";
//		}		
//		
//		Properties props = System.getProperties();
//		Context ctx = new InitialContext(props);
//		eventFacade = (AdministrationFacadeRemote) ctx.lookup("java:app/e-agenda.jar/AdministrationFacadeBean!ejb.AdministrationFacadeRemote");
//		// Creamos la direccion antes de crear el evento
//		this.setLocation(new AddressJPA(null, this.street, this.city, this.country, this.number));
//		dataEvent = new EventJPA(this.name, this.description, this.picture, this.getSelectedWords(), this.location, this.iniDate, this.endDate, this.comments, this.rattings, this.categories, this.company);
//		OpStatus op = eventFacade.addEvent(dataEvent);
//		
//		if ("OK".equals(op.getCod()))
//			{
//			return "eventsListView";
//			}
//		else
//			{
//			this.errMsg = op.getMsg();
//			return "errorView";
//			}
//		}
//
//	public Set<WordJPA> getSelectedWords()
//		{
//		return selectedWords;
//		}
//
//	public void setSelectedWords(Set<WordJPA> selectedWords)
//		{
//		this.selectedWords = selectedWords;
//		}
//
//	public Set<WordJPA> getAllWords()
//		{
//		return allWords;
//		}
//
//	public void setAllWords(Set<WordJPA> allWords)
//		{
//		this.allWords = allWords;
//		}
//
//	public Map<String, WordJPA> getAllWordsList()
//		{
//		return allWordsList;
//		}
//
//	private void setAllWordsList(Map<String, WordJPA> allWordsList)
//		{
//			this.allWordsList = allWordsList;
//		}
//	
//	// Aux method to set initial values for the app
//	public void iniApp() throws NamingException
//		{
//		Properties props = System.getProperties();
//		Context ctx = new InitialContext(props);
//		adminRemote = (AdministrationFacadeRemote) ctx.lookup("java:app/e-agenda.jar/AdministrationFacadeBean!ejb.AdministrationFacadeRemote");
//		adminRemote.iniApp();
//		}
//
//	public String getStreet()
//		{
//			return street;
//		}
//
//	public void setStreet(String street)
//		{
//			this.street = street;
//		}
//
//	public String getCity()
//		{
//			return city;
//		}
//
//	public void setCity(String city)
//		{
//			this.city = city;
//		}
//
//	public String getCountry()
//		{
//			return country;
//		}
//
//	public void setCountry(String country)
//		{
//			this.country = country;
//		}
//
//	public String getNumber()
//		{
//			return number;
//		}
//
//	public void setNumber(String number)
//		{
//			this.number = number;
//		}
//	
//	public void companiesList() throws NamingException
//		{
//		Properties props = System.getProperties();
//		Context ctx = new InitialContext(props);
//		eventFacade = (AdministrationFacadeRemote) ctx.lookup("java:app/e-agenda.jar/AdministrationFacadeBean!ejb.AdministrationFacadeRemote");
//		this.setCompanies(eventFacade.listCompanies());
//		if (this.getCompanies() != null && this.getCompanies().size() > 0)
//			{
//			for (CompanyJPA comp : this.getCompanies())
//				{
//				this.getCompaniesList().add( new SelectItem(comp.getId(), comp.getName()) );
//				}
//			}
//		}
//	
//	public void setCompanies(Set<CompanyJPA> companies) 
//		{
//		this.companies = companies;	
//		}
//	
//	public Set<CompanyJPA> getCompanies() 
//		{
//		return this.companies;
//		}
//	
//	public void setCompaniesList(Set<SelectItem> companiesList) 
//		{
//		this.companiesList = companiesList;	
//		}
//	
//	public Set<SelectItem> getCompaniesList() 
//		{
//		return this.companiesList;	
//		}
//
//	public void companyValueChanged(ValueChangeEvent companyChanged)
//		{
//		CompanyJPA comp = (CompanyJPA) adminRemote.getCompanyById( (long)((int) companyChanged.getNewValue()) );
//		this.setCompany(comp);
//		}
//
//	public CompanyJPA getCompany()
//		{
//		return company;
//		}
//	
//	public void setCompany(CompanyJPA company)
//		{
//		this.company = company;
//		}
//	
//	public int getSelectedCompany()
//		{
//		return selectedCompany;
//		}
//	
//	public void setSelectedCompany(int selectedCompany)
//		{
//		this.selectedCompany = selectedCompany;
//		}
//
//	
//	}
