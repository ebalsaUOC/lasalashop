package jpa;
import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * JPA Class CategoryJPA
 */
@Entity
@Table(name="lasalashop.category")
public class CategoryPrototypeJPA implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private	Integer id;	
	private String name;
	private String description; 
	
	/**
	 * Class constructor methods
	 */
		
	public CategoryPrototypeJPA() {		
		this.id=getId();		
	}
	
		
	/**
	 *  Methods get/set the fields of database
	 *  Id Primary Key field
	 */
	@GeneratedValue
	@Id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
