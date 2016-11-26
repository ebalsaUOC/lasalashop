/*
 * 
 * @author ebalsa@uoc.edu
 */
package managedbeanEx;

import java.io.Serializable;
import java.util.*;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.faces.context.FacesContext;

import jpaEx.AddressJPA;
import jpaEx.LanguageJPA;
import jpaEx.UserJPA;
import ejbEx.AdministrationFacadeRemote;
import ejbEx.OpStatus;

/**
 * Managed Bean LoginMBean
 */
@ManagedBean(name = "registerMBean")
@SessionScoped
public class RegisterMBean implements Serializable
	{
	private static final long serialVersionUID = 1L;
	@EJB
	private AdministrationFacadeRemote adminRemote;
	protected String name;
	protected String surname;
	protected String preferedLanguage;
	protected String country;
	protected String city;
	protected String street;
	protected String number;
	protected String password;
	protected String email;
	protected String errMsg;
	protected Collection<LanguageJPA> languages = new ArrayList<LanguageJPA>();
	protected Collection<String> languagesDescList = new ArrayList<String>();
	protected String language = "Seleccione lenguaje";

	public RegisterMBean() throws Exception
		{
		this.languageList();
		}

	public String register(String nif, String name, String surname, String country, String city, String number, String street, String password, String email) throws Exception
		{
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		adminRemote = (AdministrationFacadeRemote) ctx.lookup("java:app/e-agenda.jar/AdministrationFacadeBean!ejb.AdministrationFacadeRemote");
		OpStatus op = new OpStatus();
		AddressJPA address = null;
		if (nif==null || "".equals(nif.trim()) ||
				name==null || "".equals(name.trim()) ||
				surname==null || "".equals(surname.trim()) ||
				country==null || "".equals(country.trim()) ||
				city==null || "".equals(city.trim()) ||
				number==null || "".equals(number.trim()) ||
				street==null || "".equals(street.trim()) ||
				password==null || "".equals(password.trim()) ||
				email==null || "".equals(email.trim()) 
				){
			this.errMsg = "Ompli tots els camps si us plau";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.errMsg));
			return "registerView";
			
		}
				
		// Instantiate Address
		try
			{
			address = new AddressJPA();
			address.setStreet(street);
			address.setCity(city);
			address.setCountry(country);
			address.setNumber(number);
			adminRemote.addAddress(address);
			}
		catch (Exception e)
			{
			this.errMsg = "Direccion erronea";
			}
		// Set Language
		List<LanguageJPA> langList = new ArrayList<>();
		langList.addAll(languages);
		// Default....
		LanguageJPA preferedLanguage = langList.get(0);
		// Try to set:
		for (LanguageJPA l : this.languages)
			{
			if (l.getDescription().equals(this.preferedLanguage))
				{
				preferedLanguage = l;
				}
			}
		UserJPA user = new UserJPA(nif, name, surname, preferedLanguage, address, password, email);
		op = adminRemote.register(user);
		
		if ("OK".equals(op.getCod())){
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
			session.setAttribute("user", user);
			return "headerUserView";
		}
	
		else
			{
			this.errMsg = op.getMsg();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.errMsg));
			return "registerView";
			}
		}

	public AdministrationFacadeRemote getAdminRemote()
		{
		return adminRemote;
		}

	public void setAdminRemote(AdministrationFacadeRemote adminRemote)
		{
		this.adminRemote = adminRemote;
		}

	public static long getSerialversionuid()
		{
		return serialVersionUID;
		}

	public String getName()
		{
		return name;
		}

	public void setName(String name)
		{
		this.name = name;
		}

	public String getSurname()
		{
		return surname;
		}

	public void setSurname(String surname)
		{
		this.surname = surname;
		}

	public String getPreferedLanguage()
		{
		return preferedLanguage;
		}

	public void setPreferedLanguage(String preferedLanguage)
		{
		this.preferedLanguage = preferedLanguage;
		}

	public String getCountry()
		{
		return country;
		}

	public void setCountry(String country)
		{
		this.country = country;
		}

	public String getCity()
		{
		return city;
		}

	public void setCity(String city)
		{
		this.city = city;
		}

	public String getStreet()
		{
		return street;
		}

	public void setStreet(String street)
		{
		this.street = street;
		}

	public String getNumber()
		{
		return number;
		}

	public void setNumber(String number)
		{
		this.number = number;
		}

	public Collection<LanguageJPA> getLanguages()
		{
		return languages;
		}

	public void setLanguages(Collection<LanguageJPA> languages)
		{
		this.languages = languages;
		}

	public String getPassword()
		{
		return password;
		}

	public void setPassword(String password)
		{
		this.password = password;
		}

	public String getEmail()
		{
		return email;
		}

	public void setEmail(String email)
		{
		this.email = email;
		}

	public String getErrMsg()
		{
		return errMsg;
		}

	public void setErrMsg(String errMsg)
		{
		this.errMsg = errMsg;
		}

	public String getLanguage()
		{
		return language;
		}

	public void setLanguage(String language)
		{
		this.language = language;
		}

	public Collection<String> getLanguagesDescList()
		{
		return languagesDescList;
		}

	public void setLanguagesDescList(Collection<String> languagesDescList)
		{
		this.languagesDescList = languagesDescList;
		}

	private void languageList() throws NamingException
		{
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		adminRemote = (AdministrationFacadeRemote) ctx.lookup("java:app/e-agenda.jar/AdministrationFacadeBean!ejb.AdministrationFacadeRemote");
		Collection<LanguageJPA> languageCollection = (Collection<LanguageJPA>) adminRemote.listLanguages();
		this.languages = languageCollection;
		if (this.languages != null && this.languages.size() > 0)
			{
			for (LanguageJPA l : this.languages)
				{
				this.languagesDescList.add(l.getDescription());
				}
			}
		}

	public void languageValueChanged(ValueChangeEvent languageChanged)
		{
		this.language = languageChanged.getNewValue().toString();
		}
	}
