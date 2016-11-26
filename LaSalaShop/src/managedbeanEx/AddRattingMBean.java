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
@ManagedBean(name = "addRattingMBean")
@SessionScoped
public class AddRattingMBean implements Serializable
	{
	private static final long serialVersionUID = 1L;
	@EJB
	private EventFacadeRemote eventRemote;
	protected UserJPA user;
	protected String errMsg;
	protected EventJPA event;
	protected String ratting;

	public AddRattingMBean() throws Exception
		{
		FacesContext facesContext = FacesContext.getCurrentInstance();
	    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		
		 this.user = (UserJPA) session.getAttribute("user");
		}

	public String rate(EventJPA event)
		{
		this.event = event;
		return "addRattingView";
		}

	public String addRatting(String ratting) throws Exception
		{
		
		if(ratting.length()>3){
			ratting=ratting.substring(0,3);	
		}
		
		if(ratting==null || !isNumeric(ratting) || 
				Integer.valueOf(ratting)<0 || Integer.valueOf(ratting)>10 ){
			this.errMsg = "Valor incorrecte. El nombre ha d'estar compres entre 0 i 10";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.errMsg));
			return "addRattingView";
		}
		
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		eventRemote = (EventFacadeRemote) ctx.lookup("java:app/e-agenda.jar/EventFacadeBean!ejb.EventFacadeRemote");
		OpStatus op = new OpStatus();
		op = eventRemote.addRatting(this.user, this.event, Integer.valueOf(ratting));
		if ("OK".equals(op.getCod()))
			{

			this.event.addRatting(Integer.valueOf(ratting));
			return "eventDetailView";
			}
		else
			{
			this.errMsg = op.getMsg();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.errMsg));
			return "addRattingView";
			}
		}

	public EventFacadeRemote getEventRemote()
		{
		return eventRemote;
		}

	public void setEventRemote(EventFacadeRemote eventRemote)
		{
		this.eventRemote = eventRemote;
		}

	public String getErrMsg()
		{
		return errMsg;
		}

	public void setErrMsg(String errMsg)
		{
		this.errMsg = errMsg;
		}

	public static long getSerialversionuid()
		{
		return serialVersionUID;
		}

	public UserJPA getUser()
		{
		return user;
		}

	public void setUser(UserJPA user)
		{
		this.user = user;
		}

	public EventJPA getEvent()
		{
		return event;
		}

	public void setEvent(EventJPA event)
		{
		this.event = event;
		}

	public String getRatting()
		{
		return ratting;
		}

	public void setRatting(String ratting)
		{
		this.ratting = ratting;
		}
	
	public boolean isNumeric(String s) {  
	    return s.matches("[-+]?\\d*\\.?\\d+");  
	}  
	}
