/*
 * 
 * @author ebalsa@uoc.edu
 */
package managedbean;

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

import jpa.DistribuidoraJPA;
import ejb.LibroFacadeRemote;


/**
 * Managed Bean LoginMBean
 */
@ManagedBean(name = "addBookMBean")
@SessionScoped
public class AddBookMBean implements Serializable
	{
	private static final long serialVersionUID = 1L;
	@EJB
	private LibroFacadeRemote adminRemote;
	private  String titulo;
	private  String autor;
	private  String editorial;  
	private  String isbn;
	private double netoCompra;
	private double pvp;
	private int unidades;
	private String distribuidora;
	private Collection<DistribuidoraJPA> distribuidoras = new ArrayList<DistribuidoraJPA>();
	private Collection<String> distribuidorasDescList = new ArrayList<String>();
	
	public AddBookMBean() throws Exception
		{
		this.distribuidorasList();
		}

	public String register(String titulo, String autor, String isbn, double precioCompra, 
			double pvp, int unidades) throws Exception{
//		Properties props = System.getProperties();
//		Context ctx = new InitialContext(props);
//		adminRemote = (AdministrationFacadeRemote) ctx.lookup("java:app/e-agenda.jar/AdministrationFacadeBean!ejb.AdministrationFacadeRemote");
//		OpStatus op = new OpStatus();
//		AddressJPA address = null;
//		if (nif==null || "".equals(nif.trim()) ||
//				name==null || "".equals(name.trim()) ||
//				surname==null || "".equals(surname.trim()) ||
//				country==null || "".equals(country.trim()) ||
//				city==null || "".equals(city.trim()) ||
//				number==null || "".equals(number.trim()) ||
//				street==null || "".equals(street.trim()) ||
//				password==null || "".equals(password.trim()) ||
//				email==null || "".equals(email.trim()) 
//				){
//			this.errMsg = "Ompli tots els camps si us plau";
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.errMsg));
//			return "registerView";
//			
//		}
//				
//		// Instantiate Address
//		try
//			{
//			address = new AddressJPA();
//			address.setStreet(street);
//			address.setCity(city);
//			address.setCountry(country);
//			address.setNumber(number);
//			adminRemote.addAddress(address);
//			}
//		catch (Exception e)
//			{
//			this.errMsg = "Direccion erronea";
//			}
//		// Set Language
//		List<LanguageJPA> langList = new ArrayList<>();
//		langList.addAll(languages);
//		// Default....
//		LanguageJPA preferedLanguage = langList.get(0);
//		// Try to set:
//		for (LanguageJPA l : this.languages)
//			{
//			if (l.getDescription().equals(this.preferedLanguage))
//				{
//				preferedLanguage = l;
//				}
//			}
//		UserJPA user = new UserJPA(nif, name, surname, preferedLanguage, address, password, email);
//		op = adminRemote.register(user);
//		
//		if ("OK".equals(op.getCod())){
//			FacesContext facesContext = FacesContext.getCurrentInstance();
//			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
//			session.setAttribute("user", user);
//			return "headerUserView";
//		}
//	
//		else
//			{
//			this.errMsg = op.getMsg();
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.errMsg));
//			return "registerView";
//			}
		return "Prototipo";
		}

	

	private void distribuidorasList() throws NamingException
		{
		//PAra la version definitiva, recogemos de la BBDD las distribuidoras, y creamos dos listas, 
		//una con las distribuidoras en si, y otra con el nombre para pasarla a la vista.
//		Properties props = System.getProperties();
//		Context ctx = new InitialContext(props);
//		distribuidirasRemote = (DiistribuidorasFacadeRemote) ctx.lookup("java:app/e-agenda.jar/AdministrationFacadeBean!ejb.AdministrationFacadeRemote");
//		Collection<LanguageJPA> languageCollection = (Collection<LanguageJPA>) adminRemote.listLanguages();
//		this.languages = languageCollection;
//		if (this.languages != null && this.languages.size() > 0)
//			{
//			for (LanguageJPA l : this.languages)
//				{
//				this.languagesDescList.add(l.getDescription());
//				}
//			}
		//Para los prototipos creamos las listas de descripciones en el mBean
		this.distribuidorasDescList.add("DISTRIB_1");
		this.distribuidorasDescList.add("dist_2");
		this.distribuidorasDescList.add("La Otra S.A.");
		
		
	}

	public void distribuidoraValueChanged(ValueChangeEvent distribuidoraChanged){
		
		this.distribuidora = distribuidoraChanged.getNewValue().toString();
		
	}

	public LibroFacadeRemote getAdminRemote() {
		return adminRemote;
	}

	public void setAdminRemote(LibroFacadeRemote adminRemote) {
		this.adminRemote = adminRemote;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public double getNetoCompra() {
		return netoCompra;
	}

	public void setNetoCompra(double netoCompra) {
		this.netoCompra = netoCompra;
	}

	public double getPvp() {
		return pvp;
	}

	public void setPvp(double pvp) {
		this.pvp = pvp;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public String getDistribuidora() {
		return distribuidora;
	}

	public void setDistribuidora(String distribuidora) {
		this.distribuidora = distribuidora;
	}

	public Collection<DistribuidoraJPA> getDistribuidoras() {
		return distribuidoras;
	}

	public void setDistribuidoras(Collection<DistribuidoraJPA> distribuidoras) {
		this.distribuidoras = distribuidoras;
	}

	public Collection<String> getDistribuidorasDescList() {
		return distribuidorasDescList;
	}

	public void setDistribuidorasDescList(Collection<String> distrivuidorasDescList) {
		this.distribuidorasDescList = distrivuidorasDescList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
