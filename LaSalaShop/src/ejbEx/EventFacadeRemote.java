package ejbEx;

import java.util.List;

import javax.ejb.Remote;

import jpaEx.CategoryJPA;
import jpaEx.CommentJPA;
import jpaEx.EventJPA;
import jpaEx.UserJPA;

@Remote
public interface EventFacadeRemote
	{
	public OpStatus sendComment(UserJPA user, EventJPA event, String comment);

	public OpStatus addRatting(UserJPA user, EventJPA event, int ratting);

	public OpStatus suggest(EventJPA event, String email);

	public OpStatus addToFavorites(UserJPA user, EventJPA event);

	// public OpStatus addCategory(EventJPA event, CategoryJPA category); // No lo hacemos por aqui, si no por la parte de administracion
	
	public List<EventJPA> listAllFavorites(String user);

	public List<EventJPA> listAllEvents();
	
	public List<CommentJPA> listAllComments(int event_id);

	public List<EventJPA> findEventsByCategory(CategoryJPA category);

	public List<EventJPA> findEventsByName(String name);

	public List<EventJPA> findEventsByWord(String word);

	public EventJPA showEvent(int eventId);

	public List<EventJPA> listFavoriteEvents(String userId) throws Exception;
	}
