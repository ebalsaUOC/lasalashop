package jpa;
import java.io.Serializable;
import java.util.*;

import javax.persistence.*;



/**
*
* @author ebalsa@uoc.edu
*/
@Entity
@Table(name="lasalashop.distributors")
public class DistributorJPA implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;
    private String name;
    private String legalName;
    private String cif;
    private String contactPerson;
    private String distEmail;
    private String contactEmail;
    private String mainTelNumber;
    private String contactTelNumber;
    
    private Set<BookJPA> books = new HashSet<BookJPA>();
	
	/**
	 * Class constructor methods
	 */
		
	public DistributorJPA() {		
		this.id=getId();		
	}
	
	public DistributorJPA(String name, String legalName, String cif,
			String contactPerson, String distEmail, String contactEmail,
			String mainTelNumber, String contactTelNumber) {
		super();
		this.name = name;
		this.legalName = legalName;
		this.cif = cif;
		this.contactPerson = contactPerson;
		this.distEmail = distEmail;
		this.contactEmail = contactEmail;
		this.mainTelNumber = mainTelNumber;
		this.contactTelNumber = contactTelNumber;
		this.books = books;
	}
		
	/**
	 *  Methods get/set the fields of database
	 *  Id Primary Key field
	 */
	@GeneratedValue
	@Id
	@Column(name = "ID_DISTRIBUTOR")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "distributor")
	public Set<BookJPA> getBooks() {
		return books;
	}


	public void setBooks(Set<BookJPA> books) {
		this.books = books;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getCif() {
		return cif;
	}


	public void setCif(String cIF) {
		cif = cIF;
	}


	public String getContactPerson() {
		return contactPerson;
	}


	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}


	public String getDistEmail() {
		return distEmail;
	}


	public void setDistEmail(String distEmail) {
		this.distEmail = distEmail;
	}


	public String getContactEmail() {
		return contactEmail;
	}


	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}


	public String getMainTelNumber() {
		return mainTelNumber;
	}


	public void setMainTelNumber(String mainTelNumber) {
		this.mainTelNumber = mainTelNumber;
	}


	public String getContactTelNumber() {
		return contactTelNumber;
	}


	public void setContactTelNumber(String contactTelNumber) {
		this.contactTelNumber = contactTelNumber;
	}


	public String getLegalName() {
		return legalName;
	}


	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	
	
}
