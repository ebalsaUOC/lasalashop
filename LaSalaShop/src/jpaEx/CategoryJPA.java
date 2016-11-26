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

/**
 * create cdominguez JPA Class CategoryJPA
 */
@Entity
@Table(name = "eagenda.category")
public class CategoryJPA implements Serializable
	{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String description;
	private Set<EventJPA> events;

	public CategoryJPA()
		{
		this.id = getId();
		}

	public CategoryJPA(String name, String description)
		{
		this();
		this.name = name;
		this.description = description;
		}

	/**
	 * Methods get/set the fields of database Id Primary Key field
	 */
	@GeneratedValue
	@Id
	@Column(name = "CATEGORY_ID")
	public Integer getId()
		{
		return id;
		}

	public void setId(Integer id)
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

	public static long getSerialversionuid()
		{
		return serialVersionUID;
		}

	@ManyToMany(fetch = FetchType.EAGER, mappedBy="categories")
	public Set<EventJPA> getEvents()
		{
		return events;
		}

	public void setEvents(Set<EventJPA> events)
		{
		this.events = events;
		}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 * 
	 * Este metodo y el siguiente lo implementamos para poder guardar esta clase en un desplegable
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
		if (!(object instanceof CategoryJPA))
			{
			return false;
			}
		CategoryJPA other = (CategoryJPA) object;
		if ((this.id == null && other.id != null) || (this.id != null && ! this.id.equals(other.id))) 
			{
			return false;
			}
		return true;
		}
	}