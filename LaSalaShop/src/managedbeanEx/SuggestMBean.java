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
import javax.naming.Context;
import javax.naming.InitialContext;

import jpaEx.EventJPA;
import ejbEx.EventFacadeRemote;
import ejbEx.OpStatus;


/**
 * Managed Bean LoginMBean
 */
@ManagedBean(name = "suggestMBean")
@SessionScoped
public class SuggestMBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private EventFacadeRemote eventRemote;
	protected String email;
	protected String errMsg;
	protected EventJPA event;
		
	public SuggestMBean() throws Exception
	{
	
	}
	
	public String openSuggest(EventJPA e){
		this.event = e;
		
		return "suggestView";
	}

	public String suggest(String email ) throws Exception	{	
		
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		eventRemote = (EventFacadeRemote)ctx.lookup("java:app/e-agenda.jar/EventFacadeBean!ejb.EventFacadeRemote"); 
		
		OpStatus op = new OpStatus();
		
		if(email==null || "".equals(email.trim()) ){
			this.errMsg ="Ompli tots els camps si us plau";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.errMsg));
			return "suggestView";
		}	
		
		op = eventRemote.suggest(this.event, email);
		
			
		if("OK".equals(op.getCod())) return "eventDetailView";
		
		else{
			this.errMsg = op.getMsg();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.errMsg));
			return "suggestView";
		}
	}

	public EventFacadeRemote getEventRemote() {
		return eventRemote;
	}

	public void setEventRemote(EventFacadeRemote eventRemote) {
		this.eventRemote = eventRemote;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EventJPA getEvent() {
		return event;
	}

	public void setEventId(EventJPA event) {
		this.event = event;
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
	
	
}
