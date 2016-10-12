package jpa;
import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

import domain.EnumeratedStatus;

/**
*
* @author ebalsa@uoc.edu
*/
@Entity
@Table(name="lasalashop.distribuidoras")
public class DistribuidoraJPA implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;
    private String name;
    private Set<LibroJPA> books = new HashSet<LibroJPA>();
	
	/**
	 * Class constructor methods
	 */
		
	public DistribuidoraJPA() {		
		this.id=getId();		
	}
	
		
	/**
	 *  Methods get/set the fields of database
	 *  Id Primary Key field
	 */
	@GeneratedValue
	@Id
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


	public Set<LibroJPA> getBooks() {
		return books;
	}


	public void setBooks(Set<LibroJPA> books) {
		this.books = books;
	}


	public void setId(long id) {
		this.id = id;
	}
	
	
}
