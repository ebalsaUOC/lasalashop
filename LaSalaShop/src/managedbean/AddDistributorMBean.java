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
import javax.naming.Context;
import javax.naming.InitialContext;
import jpa.DistributorJPA;
import domain.OpStatus;
import ejb.BookManagementFacade;


/**
 * Managed Bean AddDistributorMBean
 */
@ManagedBean(name = "addDistributorMBean")
@SessionScoped
public class AddDistributorMBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@EJB
	private BookManagementFacade bookRemote;
	private String errMsg;
	private String okMsg;
	
	public AddDistributorMBean() throws Exception{	
	}

	public String addDistributor(String name, String legalName, String cif,
			String contactPerson, String distEmail, String contactEmail,
			String mainTelNumber, String contactTelNumber) throws Exception	{	
		
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		bookRemote = (BookManagementFacade)ctx.lookup("java:app/lasalashop.jar/BookManagementImpl!ejb.BookManagementFacade"); 
		
		OpStatus op = new OpStatus();
		
		if(false ){
			//TODO: LOGICA DE VALIDACIONES
			this.errMsg ="Rellene todos los campos por favor";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.errMsg));
			return "addDistributorView";
		}	
		
		DistributorJPA d = new DistributorJPA(name, legalName, cif, contactPerson, 
				distEmail, contactEmail, mainTelNumber, contactTelNumber);
		op = bookRemote.addDistributor(d);
						
		if("OK".equals(op.getCod())){
			this.okMsg = op.getMsg();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.okMsg));
		
			return "booksMainView";
		}
		
		else{
			this.errMsg = op.getMsg();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.errMsg));
			return "addDistributorView";
		}
	}


	
	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
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

	public String getOkMsg() {
		return okMsg;
	}

	public void setOkMsg(String okMsg) {
		this.okMsg = okMsg;
	}
	
}
