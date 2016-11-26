package jpaEx;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "eagenda.word")
public class WordJPA implements Serializable
	{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String tag;
	private Set<EventJPA> events;

	public WordJPA()
		{
		this.id = getId();
		}

	public WordJPA(String comment, Set<EventJPA> events, String tag)
		{
		this();
		this.tag = tag;
		this.events = events;
		}

	@Id
	@GeneratedValue
	@Column(name = "WORD_ID")
	public Integer getId()
		{
		return id;
		}

	public void setId(Integer id)
		{
		this.id = id;
		}

	public String getTag()
		{
		return tag;
		}

	public void setTag(String tag)
		{
		this.tag = tag;
		}

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "words")
	public Set<EventJPA> getEvents()
		{
		return events;
		}

	public void setEvents(Set<EventJPA> events)
		{
		this.events = events;
		}

	public static long getSerialversionuid()
		{
		return serialVersionUID;
		}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 * 
	 * Este metodo y el siguiente lo implementamos para poder guardar esta clase en una lista desplegable
	 */
	@Override
	public int hashCode()
		{
		int hash=0;
		hash += (this.id != null ? this.id.hashCode() : 0);
		return hash;
		}
	
	@Override
	public boolean equals(Object object)
		{
		if (!(object instanceof WordJPA))
			{
			return false;
			}
		WordJPA other = (WordJPA) object;
		if ((this.id == null && other.id != null) || (this.id != null && ! this.id.equals(other.id))) 
			{
			return false;
			}
		return true;
		}
	}
