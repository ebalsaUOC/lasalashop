package jpaEx;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "eagenda.event")
public class EventJPA implements Serializable
	{
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String description;
	private String picture;
	private Set<WordJPA> words;
	private float ratting;
	private AddressJPA location;
	private Date iniDate;
	private Date endDate;
	private Set<CommentJPA> comments = new HashSet<CommentJPA>();
	private Set<RattingJPA> rattings = new HashSet<RattingJPA>();
	private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	private Set<CategoryJPA> categories;
	private CompanyJPA company;
	
	public static Comparator<EventJPA> COMPARE_BY_ID = new Comparator<EventJPA>()
		{
			public int compare(EventJPA one, EventJPA other)
				{
				return ((Integer)one.id).compareTo((Integer)other.id);
				}
		};

	public EventJPA()
		{
		super();
		this.id = getId();
		}

	public EventJPA(int id, String name, String description, String picture, Set<WordJPA> words, AddressJPA location, Date iniDate, Date endDate, Set<CommentJPA> comments,
			Set<RattingJPA> rattings, Set<CategoryJPA> categories, CompanyJPA company)
		{
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.picture = picture;
		this.words = words;
		this.location = location;
		this.iniDate = iniDate;
		this.endDate = endDate;
		this.comments = comments;
		this.rattings = rattings;
		this.setCategories(categories);
		this.company = company;
		}

	// Agregado por Xesco para no tener que hacer 5000 sets
	public EventJPA(String name, String description, String picture, Set<WordJPA> words, AddressJPA location, Date iniDate, Date endDate, Set<CommentJPA> comments,
			Set<RattingJPA> rattings, Set<CategoryJPA> categories, CompanyJPA company)
		{
		super();
		this.id = getId();
		this.name = name;
		this.description = description;
		this.picture = picture;
		this.words = words;
		this.location = location;
		this.iniDate = iniDate;
		this.endDate = endDate;
		this.comments = comments;
		this.rattings = rattings;
		this.setCategories(categories);
		this.company = company;
		}

	@Id
	@GeneratedValue
	@Column(name = "EVENT_ID")
	public int getId()
		{
		return id;
		}

	public void setId(int id)
		{
		this.id = id;
		}

	public String getName()
		{
		return name;
		}

	public void setName(String name)
		{
		this.name = name;
		}

	public String getDescription()
		{
		return description;
		}

	public void setDescription(String description)
		{
		this.description = description;
		}

	public String getPicture()
		{
		return picture;
		}

	public void setPicture(String picture)
		{
		this.picture = picture;
		}

	public float getRatting()
		{
		this.ratting = 0;
		if (this.rattings != null && this.rattings.size() > 0)
			{
			for (RattingJPA r : this.rattings)
				{
				ratting += r.getRatting();
				}
			ratting /= this.rattings.size();
			}
		return ratting;
		}

	public void setRatting(float ratting)
		{
		this.ratting = ratting;
		}

	public void addRatting(float ratting)
		{
		this.rattings.add(new RattingJPA(ratting, this));
		}

	public AddressJPA getLocation()
		{
		return location;
		}

	public void setLocation(AddressJPA location)
		{
		this.location = location;
		}

	public Date getIniDate()
		{
		return iniDate;
		}

	public void setIniDate(Date iniDate)
		{
		this.iniDate = iniDate;
		}

	public Date getEndDate()
		{
		return endDate;
		}

	public void setEndDate(Date endDate)
		{
		this.endDate = endDate;
		}

	public SimpleDateFormat getFormat()
		{
		return format;
		}

	public void setFormat(SimpleDateFormat format)
		{
		this.format = format;
		}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "event")
	public Set<CommentJPA> getComments()
		{
		return comments;
		}

	public void setComments(Set<CommentJPA> comments)
		{
		this.comments = comments;
		}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "event")
	public Set<RattingJPA> getRattings()
		{
		return rattings;
		}

	public void setRattings(Set<RattingJPA> rattings)
		{
		this.rattings = rattings;
		}

	public static long getSerialversionuid()
		{
		return serialVersionUID;
		}

	@JoinTable(name = "eagenda.event_category", joinColumns = { @JoinColumn(name = "event_id") }, inverseJoinColumns = { @JoinColumn(name = "category_id") })
	@ManyToMany(fetch = FetchType.EAGER)
	public Set<CategoryJPA> getCategories()
		{
		return categories;
		}

	public void setCategories(Set<CategoryJPA> categories)
		{
		this.categories = categories;
		}

	@JoinTable(name = "eagenda.event_word", joinColumns = { @JoinColumn(name = "event_id") }, inverseJoinColumns = { @JoinColumn(name = "word_id") })
	@ManyToMany(fetch = FetchType.EAGER)
	public Set<WordJPA> getWords()
		{
		return words;
		}

	public void setWords(Set<WordJPA> words)
		{
		this.words = words;
		}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COMPANY_ID", nullable = true)
	public CompanyJPA getCompany()
		{
		return company;
		}

	public void setCompany(CompanyJPA company)
		{
		this.company = company;
		}
	}
