/*
 * 
 * @author ebalsa@uoc.edu, 2014
 * 
 */
package ejbEx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jpaEx.CategoryJPA;
import jpaEx.CommentJPA;
import jpaEx.EventJPA;
import jpaEx.RattingJPA;
import jpaEx.UserJPA;
import jpaEx.WordJPA;

@Stateless
public class EventFacadeBean implements EventFacadeRemote
	{
	// Persistence Unit Context
	@PersistenceContext(unitName = "eagenda")
	private EntityManager entman;

	// Create a new comment (User-->Comment<--Event)
	public OpStatus sendComment(UserJPA user, EventJPA event, String comment)
		{
		OpStatus op = new OpStatus();
		// Add comment & persists
		CommentJPA com = new CommentJPA(comment, user, event);
		try
			{
			entman.persist(com);
			user.getComments().add(com);
			event.getComments().add(com);
			entman.merge(user);
			entman.merge(event);
			op.setCod("OK");
			return op;
			}
		catch (Exception e)
			{
			op.setCod("KO");
			op.setMsg(e.getMessage());
			return op;
			}
		}

	// Create a new ratting (User-->Ratting<--Event)
	public OpStatus addRatting(UserJPA user, EventJPA event, int ratting)
		{
		OpStatus op = new OpStatus();
		// Add rating & persists
		RattingJPA rat = new RattingJPA(ratting, user, event);
		try
			{
			entman.persist(rat);
			user.getRattings().add(rat);
			event.getRattings().add(rat);
			entman.merge(user);
			entman.merge(event);
			op.setCod("OK");
			return op;
			}
		catch (Exception e)
			{
			op.setCod("KO");
			op.setMsg(e.getMessage());
			return op;
			}
		}

	// Suggest Event to a user with the given email
	public OpStatus suggest(EventJPA event, String email)
		{
		OpStatus op = new OpStatus();
		// Find User
		UserJPA userFetch = null;
		try
			{
			userFetch = (UserJPA) entman.createQuery("FROM UserJPA u " + " WHERE u.email = :email").setParameter("email", email).getSingleResult();
			}
		catch (Exception e)
			{
			op.setCod("KO");
			op.setMsg("User not found");
			return op;
			}
		if (userFetch == null)
			{
			op.setCod("KO");
			op.setMsg("User not found");
			return op;
			}
		// Add suggestion & persists
		try
			{
			userFetch.getSuggestedEvents().add(event);
			entman.merge(userFetch);
			op.setCod("OK");
			return op;
			}
		catch (Exception e)
			{
			op.setCod("KO");
			op.setMsg(e.getMessage());
			return op;
			}
		}

	// Add an event to user favorites list
	public OpStatus addToFavorites(UserJPA user, EventJPA event)
		{
		OpStatus op = new OpStatus();
		try
			{
			// IMPORTANTE! Por tema de sesión es mejor recuperar al usuario ya que si no hay problemas de sincronismo con la base de datos.
			// Find User
			UserJPA userFetch = null;
			try
				{
				userFetch = (UserJPA) entman.createQuery("FROM UserJPA u " + " WHERE u.nif = :nif").setParameter("nif", user.getNif()).getSingleResult();
				}
			catch (Exception e)
				{
				throw(new Exception("Usuari no trobat")); 
				}
			if (userFetch == null)
				{
				throw(new Exception("Usuari no trobat"));
				}
			userFetch.getFavoriteEvents().add(event);
			entman.merge(userFetch);
			op.setCod("OK");
			return op;
			}
		catch (Exception e)
			{
			op.setCod("KO");
			op.setMsg(e.getMessage());
			return op;
			}
		}

	@Override
	public List<EventJPA> listAllFavorites(String user)
		{
		// TODO Auto-generated method stub
		return null;
		}

	@Override
	public List<EventJPA> listAllEvents()
		{
		/*
		 * Select con todos los eventos
		 */
		@SuppressWarnings("unchecked")
		List<EventJPA> allEvents = entman.createQuery("SELECT e FROM jpa.EventJPA e order by e.id").getResultList();
		return allEvents;
		}
	
	@Override
	public List<CommentJPA> listAllComments(int event_id)
		{
		/*
		 * Select con todos los comentarios de un evento
		 */
		@SuppressWarnings("unchecked")
		List<CommentJPA> allComments = entman.createQuery("from CommentJPA where event_id="+event_id+" order by comment_id").getResultList();
		return allComments;
		}

	@Override
	public List<EventJPA> findEventsByCategory(CategoryJPA category)
		{
		// TODO Auto-generated method stub
		// List<EventJPA> filteredEvents =
		// entman.createQuery("from EventJPA where name LIKE :name order by event_id").setParameter("name",
		// "%" + name + "%").getResultList();
		List<EventJPA> eventsOfCategroy = new ArrayList<EventJPA>(category.getEvents());
		return eventsOfCategroy;
		}

	@Override
	public List<EventJPA> findEventsByName(String name)
		{
		// Select con todos los eventos filtrando por el campo nombre
		System.out.println("[EventFacadeBean]findEventsByName nombre: " + name);
		@SuppressWarnings("unchecked")
		// List<EventJPA> filteredEvents =
		// entman.createQuery("from EventJPA where name LIKE :name order by event_id").setParameter("name",
		// "%" + name + "%").getResultList();
		List<EventJPA> filteredEvents = (List<EventJPA>) entman.createQuery("Select e from EventJPA e where e.name LIKE '%" + name.trim() + "%'").getResultList();
		return filteredEvents;
		}

	@Override
	public List<EventJPA> findEventsByWord(String words)
		{
		// El que invoque a este método mal no recibirá nada a cambio
		if ((!(words.split("\\s+").length > 0 && words.split("\\s+").length <4))) 
			{
			return null;
			}
		String arrayWords[] = words.split(" ");
		String where = "where";
		for (int i=0;i<arrayWords.length;i++)
			{
			where += " w.tag = '" + arrayWords[i].trim() + "' OR";
			}
		where = where.substring(0, where.length() - 3);
		@SuppressWarnings("unchecked")
		List<WordJPA> listWordsJPA = (List<WordJPA>)entman.createQuery("Select w from WordJPA w " + where).getResultList();
		List<EventJPA> filteredEvents = new ArrayList<EventJPA>();
		if (listWordsJPA != null && listWordsJPA.size() > 0)
			{
			for (WordJPA w : listWordsJPA)
				{
				filteredEvents.addAll(w.getEvents());
				}
			}
		// Limpiamos duplicados: Eventos que aparecen varias las mismas keywords
		HashSet<EventJPA> hs = new HashSet<EventJPA>();
		hs.addAll(filteredEvents);
		filteredEvents.clear();
		filteredEvents.addAll(hs);
		return filteredEvents;
		}

	@Override
	public EventJPA showEvent(int eventId)
		{
		// TODO Auto-generated method stub
		return null;
		}

	@Override
	public List<EventJPA> listFavoriteEvents(String userId) throws Exception
		{
		// Find User
		UserJPA userFetch = null;
		List<EventJPA> favoriteEvents = null;
		try
			{
			userFetch = (UserJPA) entman.createQuery("FROM UserJPA u " + " WHERE u.nif = :nif").setParameter("nif", userId).getSingleResult();
			//favoriteEvents = (List<EventJPA>) entman.createNativeQuery("Select e from eagenda.event e inner join (select event_id from eagenda.favorite_events where user_id = '22222222B') as T on e.event_id = T.event_id order by e.event_id").getResultList();
			}
		catch (Exception e)
			{
			throw(new Exception("No s'han trobat favorits per: " + e.getMessage())); 
			}
		
		if (userFetch == null)
			{
			throw(new Exception("Usuari no trobat"));
			}
		favoriteEvents = new ArrayList<EventJPA>(userFetch.getFavoriteEvents());
		Collections.sort(favoriteEvents, EventJPA.COMPARE_BY_ID);
		return favoriteEvents;
		}
	}
