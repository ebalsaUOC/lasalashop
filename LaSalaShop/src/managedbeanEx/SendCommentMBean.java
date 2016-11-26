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
import javax.servlet.http.HttpSession;

import jpaEx.EventJPA;
import jpaEx.UserJPA;
import ejbEx.EventFacadeRemote;
import ejbEx.OpStatus;


/**
 * Managed Bean LoginMBean
 */
@ManagedBean(name = "sendCommentMBean")
@SessionScoped
public class SendCommentMBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private EventFacadeRemote eventRemote;
	protected UserJPA user;
	protected String errMsg;
	protected EventJPA event;
	protected String comment;
	
	public SendCommentMBean() throws Exception
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
	    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		
		 this.user = (UserJPA) session.getAttribute("user");	
	}
	
	public String comment(EventJPA event){
		
		this.event=event;
		return "sendCommentView";
	}

	public String sendComment(String comment ) throws Exception	{	
		
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		eventRemote = (EventFacadeRemote)ctx.lookup("java:app/e-agenda.jar/EventFacadeBean!ejb.EventFacadeRemote"); 
		
		OpStatus op = new OpStatus();
		
		if(comment==null || "".equals(comment.trim()) ){
			this.errMsg ="Ompli tots els camps si us plau";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.errMsg));
			return "sendCommentView";
		}	
		
		op = eventRemote.sendComment(this.user, this.event, comment);
						
		if("OK".equals(op.getCod())){
			return "eventDetailView";
		}
		
		else{
			this.errMsg = op.getMsg();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.errMsg));
			return "sendCommentView";
		}
	}

	public EventFacadeRemote getEventRemote() {
		return eventRemote;
	}

	public void setEventRemote(EventFacadeRemote eventRemote) {
		this.eventRemote = eventRemote;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public UserJPA getUser() {
		return user;
	}

	public void setUser(UserJPA user) {
		this.user = user;
	}

	public EventJPA getEvent() {
		return event;
	}

	public void setEvent(EventJPA event) {
		this.event = event;
	}
	
	
}
