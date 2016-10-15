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
    private Set<BookJPA> books = new HashSet<BookJPA>();
	
	/**
	 * Class constructor methods
	 */
		
	public DistributorJPA() {		
		this.id=getId();		
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
	
	
}
