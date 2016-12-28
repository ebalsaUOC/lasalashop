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
	private String distributor;
	private Collection<DistributorJPA> distributors = new ArrayList<DistributorJPA>();
	private Collection<String> distributorsDescList = new ArrayList<String>();
	private String errMsg;
	private String okMsg;
	
	public AddBookMBean() throws Exception{
		
		//Get Distributors description onLoad
		distributorsDescList();	
	}

	public String addBook(String title, String author, String editor,
			String isbn, String price, String pvp,
			String units) throws Exception	{	
		
		//lookup for business class
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		bookRemote = (BookManagementFacade)ctx.lookup("java:app/lasalashop.jar/BookManagementImpl!ejb.BookManagementFacade"); 
		
		//Instantiate the return object
		OpStatus op = new OpStatus();
		
		//Input validations OK or ERR msg
		if(false ){
			//TODO: CREATE ALL VALIDATION LOGIC HERE
			this.errMsg ="Rellene todos los campos por favor";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.errMsg));
			return "addBookView";
		} else if(this.distributor==null){
			this.errMsg ="ACCION NO DISPONIBLE: Es necesario crear alguna Distribuidora en el sistema previamente";
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
		b.setCopy(Integer.valueOf(units));
		//Select Distributor form inner list and set
		DistributorJPA distToSet = null;
		for(DistributorJPA d: this.distributors){
			if(d.getName().equalsIgnoreCase(this.distributor)){
				distToSet=d;
			}
		}
		b.setDistributor(distToSet);
		
		//Call Business to store Book
		op = bookRemote.addBookB(b);
						
		//Parse return from business layer
		if("OK".equals(op.getCod())){
			this.okMsg = op.getMsg();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.okMsg));
			//Redirect to main
			return "lasalashopMainTemplate";
		}
		
		else{
			this.errMsg = op.getMsg();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.errMsg));
			return "addBookView";
		}
	}

	

	private void distributorsDescList() throws Exception{
		//We manage 2 different lists. One to store the system Distributors and other to contains the name descriptor to show in the view
		//lookup for business class
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		bookRemote = (BookManagementFacade)ctx.lookup("java:app/lasalashop.jar/BookManagementImpl!ejb.BookManagementFacade"); 
		
		this.distributors = bookRemote.listAllDistributorsB();
		
		for(DistributorJPA d: this.distributors){
			this.distributorsDescList.add(d.getName());
		}
	}

	public void distributorValueChanged(ValueChangeEvent distributorChanged){
		
		this.distributor = distributorChanged.getNewValue().toString();
		
	}

	public String getDistributor() {
		return distributor;
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

	public Collection<DistributorJPA> getDistributors() {
		return distributors;
	}

	public void setDistributors(Collection<DistributorJPA> distributors) {
		this.distributors = distributors;
	}

	public Collection<String> getDistributorsDescList() {
		return distributorsDescList;
	}

	public void setDistributorsDescList(Collection<String> distributorsDescList) {
		this.distributorsDescList = distributorsDescList;
	}

	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}
}
