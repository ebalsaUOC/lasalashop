/*
 * 
 * @author ebalsa@uoc.edu, 2014
 * 
 */
package jpaEx;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "eagenda.user")
public class UserJPA implements Serializable
	{
	private static final long serialVersionUID = 1L;
	private String nif;
	private String name;
	private String surname;
	private LanguageJPA preferedLanguage;
	private AddressJPA address;
	private String password;
	private String email;
	private Set<CommentJPA> comments;
	private Set<RattingJPA> rattings;
	private Set<EventJPA> favoriteEvents;
	private Set<EventJPA> suggestedEvents;

	public UserJPA()
		{
		super();
		}

	public UserJPA(String nif, String name, String surname, LanguageJPA preferedLanguage, AddressJPA address, String password, String email)
		{
		super();
		this.nif = nif;
		this.name = name;
		this.surname = surname;
		this.preferedLanguage = preferedLanguage;
		this.address = address;
		this.password = password;
		this.email = email;
		}

	@Id
	@Column(name = "USER_ID")
	public String getNif()
		{
		return nif;
		}

	public void setNif(String nif)
		{
		this.nif = nif;
		}

	public String getSurname()
		{
		return surname;
		}

	public void setSurname(String surname)
		{
		this.surname = surname;
		}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "LANGUAGE_ID")
	public LanguageJPA getPreferedLanguage()
		{
		return preferedLanguage;
		}

	public void setPreferedLanguage(LanguageJPA preferedLanguage)
		{
		this.preferedLanguage = preferedLanguage;
		}

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ADDRESS_ID")
	public AddressJPA getAddress()
		{
		return address;
		}

	public void setAddress(AddressJPA address)
		{
		this.address = address;
		}

	public String getPassword()
		{
		return password;
		}

	public void setPassword(String password)
		{
		this.password = password;
		}

	public String getEmail()
		{
		return email;
		}

	public void setEmail(String email)
		{
		this.email = email;
		}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	public Set<CommentJPA> getComments()
		{
		return comments;
		}

	public void setComments(Set<CommentJPA> comments)
		{
		this.comments = comments;
		}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
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

	public String getName()
		{
		return name;
		}

	public void setName(String name)
		{
		this.name = name;
		}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "eagenda.FAVORITE_EVENTS", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "EVENT_ID") })
	public Set<EventJPA> getFavoriteEvents()
		{
		return favoriteEvents;
		}

	public void setFavoriteEvents(Set<EventJPA> favoriteEvents)
		{
		this.favoriteEvents = favoriteEvents;
		}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "eagenda.SUGGESTED_EVENTS", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "EVENT_ID") })
	public Set<EventJPA> getSuggestedEvents()
		{
		return suggestedEvents;
		}

	public void setSuggestedEvents(Set<EventJPA> suggestedEvents)
		{
		this.suggestedEvents = suggestedEvents;
		}
	}
