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

import domain.OpStatus;
import jpa.BookJPA;
import jpa.DistributorJPA;
import ejb.BookManagementFacade;


/**
 * Managed Bean LoginMBean
 */
@ManagedBean(name = "addBookMBean")
@SessionScoped
public class AddBookMBean implements Serializable
	{
	private static final long serialVersionUID = 1L;
	@EJB
	private BookManagementFacade bookRemote;
	private String distribuidora;
	private Collection<DistributorJPA> distribuidoras = new ArrayList<DistributorJPA>();
	private Collection<String> distribuidorasDescList = new ArrayList<String>();
	private String errMsg;
	private String okMsg;
	
	public AddBookMBean() throws Exception
		{
		this.distribuidorasList();
		}

	public String addBook(String title, String author, String editor,
			String isbn, String price, String pvp,
			String units) throws Exception	{	
		
		//lookup for business class
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		bookRemote = (BookManagementFacade)ctx.lookup("java:app/lasalashop.jar/BookManagementImpl!ejb.BookMnagementFacade"); 
		
		//Instantiate the return object
		OpStatus op = new OpStatus();
		
		//Input validations OK or ERR msg
		if(false ){
			//TODO: LOGICA DE VALIDACIONES
			this.errMsg ="Rellene todos los campos por favor";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.errMsg));
			return "addBookView";
		}	
		
		//Parse currency
		double priceD =0;
		double pvpD = 0;
		try{
			priceD=Double.parseDouble(price);
			pvpD=Double.parseDouble(pvp);
		} catch(Exception e){
			this.errMsg ="Revise los campos de percio y pvp. Formato incorrecto";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.errMsg));
			return "addBookView";
		}
		
		//Instantiate book to move data across layers
		BookJPA b = new BookJPA(title, author, editor, isbn, priceD, pvpD);
		//TODO: SET DISTRIBUTOR
		
		op = bookRemote.addBookB(b);
						
		//Parse return from business layer
		if("OK".equals(op.getCod())){
			this.okMsg = op.getMsg();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.okMsg));
			//TODO:REDIRIGIR A PANTALLA MAIN
			return "lasalashopMainTemplate";
		}
		
		else{
			this.errMsg = op.getMsg();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.errMsg));
			return "addBookView";
		}
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
		
		//PROBAR A CAMBIAR LA VISTA PARA QUE TRABAJE CON DISTRIBUIDORAS Y ENSEÑE SU DESCRIPCION
		//NOS AHORRAMOS 2 PASOS
	}

	public void distribuidoraValueChanged(ValueChangeEvent distribuidoraChanged){
		
		this.distribuidora = distribuidoraChanged.getNewValue().toString();
		
	}

	public String getDistribuidora() {
		return distribuidora;
	}

	public void setDistribuidora(String distribuidora) {
		this.distribuidora = distribuidora;
	}

	public Collection<DistributorJPA> getDistribuidoras() {
		return distribuidoras;
	}

	public void setDistribuidoras(Collection<DistributorJPA> distribuidoras) {
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

	public BookManagementFacade getBookRemote() {
		return bookRemote;
	}

	public void setBookRemote(BookManagementFacade bookRemote) {
		this.bookRemote = bookRemote;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getOkMsg() {
		return okMsg;
	}

	public void setOkMsg(String okMsg) {
		this.okMsg = okMsg;
	}
}
