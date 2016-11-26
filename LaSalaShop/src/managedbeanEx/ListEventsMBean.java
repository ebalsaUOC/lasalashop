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
@ManagedBean(name = "listEvents")
@RequestScoped
public class ListEventsMBean
	{
	@EJB
	private EventFacadeRemote events;
	private List<EventJPA> eventList = new ArrayList<EventJPA>();
	private UserJPA user;

	public ListEventsMBean() throws Exception
		{
		// Todos los eventos
		this.viewAllEvents();
		}


	public String findEventsByName(String eventName) throws Exception
		{
		System.out.println("[ListEventsMBean] findEventsByName buscando por nombre: " + eventName);
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		events = (EventFacadeRemote) ctx.lookup("java:app/e-agenda.jar/EventFacadeBean!ejb.EventFacadeRemote");
		List<EventJPA> findEventsByName = events.findEventsByName(eventName.trim());
		if (findEventsByName != null && findEventsByName.size() > 0)
			{
			this.setEventList(findEventsByName);
			}
		else
			{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No s'han trobat events amb un nom similar a [" + eventName.trim() + "]"));
			this.setEventList(null);
			}
		return "eventListAllView";
		}

	public String findEventsByWord(String words) throws Exception
		{
		words = words.trim().toUpperCase();
		// Check máximo permitido, debería estar implementado en un validator que
		// juegue con la parte cliente y no deje hacer un request
		if ((!(words.split("\\s+").length > 0 && words.split("\\s+").length < 4)))
			{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("màxim 3 mots clau alhora, [" + words.split("\\s+").length + "] no està permès"));
			this.setEventList(null);
			return "eventListAllView";
			}
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		events = (EventFacadeRemote) ctx.lookup("java:app/e-agenda.jar/EventFacadeBean!ejb.EventFacadeRemote");
		List<EventJPA> findEventsByWords = events.findEventsByWord(words);
		if (findEventsByWords != null && findEventsByWords.size() > 0)
			{
			this.setEventList(findEventsByWords);
			}
		else
			{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No s'han trobat events amb les keywords [" + words + "]"));
			this.setEventList(null);
			}
		return "eventListAllView";
		}

	public String findEventsByCategory(CategoryJPA category) throws Exception
		{
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		this.events = (EventFacadeRemote) ctx.lookup("java:app/e-agenda.jar/EventFacadeBean!ejb.EventFacadeRemote");
		List<EventJPA> findEventsByCategory = events.findEventsByCategory(category);
		if (findEventsByCategory != null && findEventsByCategory.size() > 0)
			{
			this.setEventList(findEventsByCategory);
			}
		else
			{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No s'han trobat events dins la categoria [" + category.getName().trim() + "]"));
			this.setEventList(null);
			}
		return "eventListAllView";
		}

	public String viewAllEvents() throws Exception
		{
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		events = (EventFacadeRemote) ctx.lookup("java:app/e-agenda.jar/EventFacadeBean!ejb.EventFacadeRemote");
		this.setEventList((List<EventJPA>) events.listAllEvents());
		return "eventListAllView";
		}

	public List<EventJPA> getEventList()
		{
		return eventList;
		}

	public void setEventList(List<EventJPA> eventList)
		{
		this.eventList = eventList;
		}

	public UserJPA getUser()
		{
		return user;
		}

	public void setUser(UserJPA user)
		{
		this.user = user;
		}

	}
