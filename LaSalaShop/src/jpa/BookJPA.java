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
@Table(name="lasalashop.books")
public class BookJPA implements Serializable {

	private static final long serialVersionUID = 1L;
	
	 private long id; 
	 private  String title;
	 private  String author;
	 private  String editor;  
	 private  String isbn;
	 private double price;
	 private double pvp;
	 private DistributorJPA distributor;
	 private EnumeratedStatus status; 
	 private int copy;
	
	/**
	 * Class constructor methods
	 */
		
	public BookJPA() {		
		this.id=getId();		
	}
	
		
	public BookJPA(String title, String author, String editor, String isbn,
			double price, double pvp) {
		super();
		this.title = title;
		this.author = author;
		this.editor = editor;
		this.isbn = isbn;
		this.price = price;
		this.pvp = pvp;
	}


	/**
	 *  Methods get/set the fields of database
	 *  Id Primary Key field
	 */
	@GeneratedValue
	@Id
	@Column(name = "ID_BOOK")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;		
	}


	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_DISTRIBUTOR", nullable = false)
	public DistributorJPA getDistributor() {
		return distributor;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getEditor() {
		return editor;
	}


	public void setEditor(String editor) {
		this.editor = editor;
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public double getPvp() {
		return pvp;
	}


	public void setPvp(double pvp) {
		this.pvp = pvp;
	}


	public EnumeratedStatus getStatus() {
		return status;
	}


	public void setStatus(EnumeratedStatus status) {
		this.status = status;
	}


	public int getCopy() {
		return copy;
	}


	public void setCopy(int units) {
		this.copy = units;
	}


	public void setId(long id) {
		this.id = id;
	}


	public void setDistributor(DistributorJPA distributor) {
		this.distributor = distributor;
	}


	
	
	
}
