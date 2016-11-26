package managedbeanEx;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;








import jpaEx.AddressJPA;
import jpaEx.CategoryJPA;
import jpaEx.CommentJPA;
import jpaEx.CompanyJPA;
import jpaEx.EventJPA;
import jpaEx.WordJPA;
import ejbEx.EventFacadeRemote;

/**
 * Managed Bean ShowEventMBean
 */
@ManagedBean(name = "showEvent")
@SessionScoped
public class ShowEventMBean implements Serializable
	{
	private static final long serialVersionUID = 1L;
	@EJB
	private EventFacadeRemote showEvent;
	protected EventJPA dataEvent = new EventJPA();
	protected List<CommentJPA> commentsList = new ArrayList<CommentJPA>();
	protected List<CommentJPA> commentsListView;

	public ShowEventMBean() throws Exception
		{
		this.commentsList();
		}

	public int getId()
		{
		return this.dataEvent.getId();
		}

	public void setId(int id)
		{
		this.dataEvent.setId(id);
		}

	public String getName()
		{
		return this.dataEvent.getName();
		}

	public void setName(String name)
		{
		this.dataEvent.setName(name);
		}

	public String getDescription()
		{
		return this.dataEvent.getDescription();
		}

	public void setDescription(String description)
		{
		this.dataEvent.setDescription(description);
		}

	public String getPicture()
		{
		return this.dataEvent.getPicture();
		}

	public void setPicture(String picture)
		{
		this.dataEvent.setPicture(picture);
		}

	public String getWords()
		{
		if (this.dataEvent.getWords() == null)
			{
			return "";
			}
		String words = "";
		Iterator<WordJPA> iterator = this.dataEvent.getWords().iterator();
		while (iterator.hasNext())
			{
			WordJPA word = (WordJPA) iterator.next();
			words += word.getTag() + " ";
			}
		return words;
		}

	public float getRatting()
		{
		return this.dataEvent.getRatting();
		}

	public void setRatting(float ratting)
		{
		this.dataEvent.setRatting(ratting);
		}

	public String getLocation()
		{
		if (this.dataEvent.getLocation() == null)
			{
			return "Desconeguda";
			}
		return this.dataEvent.getLocation().getStreet() + " " + this.dataEvent.getLocation().getNumber() + ", " + this.dataEvent.getLocation().getCity() + ", " + this.dataEvent.getLocation().getCountry();
		}

	public void setLocation(AddressJPA location)
		{
		this.dataEvent.setLocation(location);
		}

	public String getIniDate()
		{
		if (this.dataEvent.getIniDate() == null)
			{
			return "Desconeguda";
			}
		return this.dataEvent.getFormat().format(this.dataEvent.getIniDate());
		}

	public void setIniDate(Date iniDate)
		{
		this.dataEvent.setIniDate(iniDate);
		}

	public String getEndDate()
		{
		if (this.dataEvent.getEndDate() == null)
			{
			return "Desconeguda";
			}
		return this.dataEvent.getFormat().format(this.dataEvent.getEndDate());
		}

	public void setEndDate(Date endDate)
		{
		this.dataEvent.setEndDate(endDate);
		}

	public void setCategories(Set<CategoryJPA> categories)
		{
		this.dataEvent.setCategories(categories);
		}

	public Set<CategoryJPA> getCategories()
		{
		return this.dataEvent.getCategories();
		}
	
	public CompanyJPA getCompany()
		{
		return this.dataEvent.getCompany();
		}
	
	public void setCompany(CompanyJPA company)
		{
		this.dataEvent.setCompany(company);
		}
	
	public String getCategoriesAsString()
		{
		if (this.dataEvent.getCategories() == null || this.dataEvent.getCategories().size() == 0)
			{
			return "[No categoritzat]";
			}
		String categories = "";
		Iterator<CategoryJPA> iterator = this.dataEvent.getCategories().iterator();
		while (iterator.hasNext())
			{
			CategoryJPA category = (CategoryJPA) iterator.next();
			categories += "[" + category.getName() + "] ";
			}
		return categories;
		}

	public void show(EventJPA event)
		{
		this.dataEvent.setId(event.getId());
		this.dataEvent.setName(event.getName());
		this.dataEvent.setPicture(event.getPicture());
		this.dataEvent.setDescription(event.getDescription());
		this.dataEvent.setCategories(event.getCategories());
		this.dataEvent.setRattings(event.getRattings());
		this.dataEvent.setWords(event.getWords());
		this.dataEvent.setIniDate(event.getIniDate());
		this.dataEvent.setEndDate(event.getEndDate());
		this.dataEvent.setLocation(event.getLocation());
		this.dataEvent.setCompany(event.getCompany());
		}

	public EventJPA getDataEvent()
		{
		return this.dataEvent;
		}

	public String commentsList() throws Exception
		{
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		showEvent = (EventFacadeRemote) ctx.lookup("java:app/e-agenda.jar/EventFacadeBean!ejb.EventFacadeRemote");
		List<CommentJPA> lista = showEvent.listAllComments(getId());
		if (lista != null && lista.size() > 0)
			{
			for (CommentJPA e : lista)
				{
				this.commentsList.add(e);
				}
			}
		else
			{
			return "errorView";
			}
		return "";
		}

	public List<CommentJPA> getCommentsListView() throws Exception
		{
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		showEvent = (EventFacadeRemote) ctx.lookup("java:app/e-agenda.jar/EventFacadeBean!ejb.EventFacadeRemote");
		commentsListView = (List<CommentJPA>) showEvent.listAllComments(getId());
		return commentsListView;
		}
	}