package managedbeanEx;

import java.util.*;

import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import jpaEx.CategoryJPA;
import jpaEx.EventJPA;
import jpaEx.UserJPA;
import ejbEx.EventFacadeRemote;

/**
 * Managed Bean ListEventsMBean
 */
@ManagedBean(name = "listFavoriteEvents")
@RequestScoped
public class ListFavoriteEventsMBean
	{
	@EJB
	private EventFacadeRemote events;
	private List<EventJPA> favoritesEventList = new ArrayList<EventJPA>();
	private UserJPA user;

	public ListFavoriteEventsMBean() throws Exception
		{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		this.setUser((UserJPA) session.getAttribute("user"));
		// Si no tenemos user en sesión no es usuario normal, por lo tanto es admin
		if (this.user != null)
			{
			this.viewFavoriteEvents(this.user.getNif());
			}
		}

	private String viewFavoriteEvents(String userId) throws NamingException
		{
		// TODO Auto-generated method stub
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		events = (EventFacadeRemote) ctx.lookup("java:app/e-agenda.jar/EventFacadeBean!ejb.EventFacadeRemote");
		try
			{
			this.setFavoritesEventList((List<EventJPA>) events.listFavoriteEvents(userId));
			}
		catch (Exception e)
			{
			// TODO Auto-generated catch block
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error buscant els teus favorits: " + e.getMessage()));
			}
		return "favoriteEventsListView";
		}

	public UserJPA getUser()
		{
		return user;
		}

	public void setUser(UserJPA user)
		{
		this.user = user;
		}

	public List<EventJPA> getFavoritesEventList()
		{
		return favoritesEventList;
		}

	public void setFavoritesEventList(List<EventJPA> favoritesEventList)
		{
		this.favoritesEventList = favoritesEventList;
		}
	}
