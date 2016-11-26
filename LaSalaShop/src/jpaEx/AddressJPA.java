package jpaEx;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "eagenda.address")
public class AddressJPA implements Serializable
	{
	private static final long serialVersionUID = 1L;
	private int id;
	private String street;
	private String city;
	private String country;
	private String number;

	public AddressJPA()
		{
		this.id = getId();
		}

	public AddressJPA(String id, String street, String city, String country, String number)
		{
		super();
		this.street = street;
		this.city = city;
		this.country = country;
		this.number = number;
		}

	@Id
	@GeneratedValue
	@Column(name = "ADDRESS_ID")
	public int getId()
		{
		return id;
		}

	public void setId(int id)
		{
		this.id = id;
		}

	public String getStreet()
		{
		return street;
		}

	public void setStreet(String street)
		{
		this.street = street;
		}

	public String getCity()
		{
		return city;
		}

	public void setCity(String city)
		{
		this.city = city;
		}

	public String getCountry()
		{
		return country;
		}

	public void setCountry(String country)
		{
		this.country = country;
		}

	public String getNumber()
		{
		return number;
		}

	public void setNumber(String number)
		{
		this.number = number;
		}

	public static long getSerialversionuid()
		{
		return serialVersionUID;
		}
	}
