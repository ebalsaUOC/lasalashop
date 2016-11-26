/*
 * @author falarconm@uoc.edu
 * 
 */
package managedbeanEx;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import jpaEx.EventJPA;
import jpaEx.UserJPA;
import ejbEx.EventFacadeRemote;

@ManagedBean(name = "addToFavoritesMBean")
@RequestScoped
public class AddToFavoritesMBean implements Serializable
	{
	private static final long serialVersionUID = 1L;
	@EJB
	private EventFacadeRemote eventRemote;

	protected String errMsg;


	public AddToFavoritesMBean() throws Exception
		{
		}
	
	public String addEventToUserFavorites (EventJPA event)
		{
		// Conseguimos el usuario de la sesion
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		UserJPA user  = (UserJPA) session.getAttribute("user");
		Properties props = System.getProperties();
		Context ctx;
		try
			{
			ctx = new InitialContext(props);
			this.eventRemote = (EventFacadeRemote) ctx.lookup("java:app/e-agenda.jar/EventFacadeBean!ejb.EventFacadeRemote");
			eventRemote.addToFavorites(user, event);
			}
		catch (NamingException e)
			{
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		return "favoriteEventsListView";
		}

	public EventFacadeRemote getEventRemote()
		{
		return eventRemote;
		}

	public void setEventRemote(EventFacadeRemote eventRemote)
		{
		this.eventRemote = eventRemote;
		}

	}
