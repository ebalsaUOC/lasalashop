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
@Table(name="lasalashop.libros")
public class LibroJPA implements Serializable {

	private static final long serialVersionUID = 1L;
	
	 private long id; 
	 private  String titulo;
	 private  String autor;
	 private  String editorial;  
	 private  String isbn;
	 private double netoCompra;
	 private double pvp;
	 private DistribuidoraJPA distribuidora;
	 private EnumeratedStatus Status; 
	 private int unidades;
	
	/**
	 * Class constructor methods
	 */
		
	public LibroJPA() {		
		this.id=getId();		
	}
	
		
	/**
	 *  Methods get/set the fields of database
	 *  Id Primary Key field
	 */
	@GeneratedValue
	@Id
	@Column(name = "ID_LIBRO")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;		
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getAutor() {
		return autor;
	}


	public void setAutor(String autor) {
		this.autor = autor;
	}


	public String getEditorial() {
		return editorial;
	}


	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public double getNetoCompra() {
		return netoCompra;
	}


	public void setNetoCompra(double netoCompra) {
		this.netoCompra = netoCompra;
	}


	public double getPvp() {
		return pvp;
	}


	public void setPvp(double pvp) {
		this.pvp = pvp;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_DISTRIBUIDORA", nullable = false)
	public DistribuidoraJPA getDistribuidora() {
		return distribuidora;
	}


	public void setDistribuidora(DistribuidoraJPA distribuidora) {
		this.distribuidora = distribuidora;
	}


	public EnumeratedStatus getStatus() {
		return Status;
	}


	public void setStatus(EnumeratedStatus status) {
		Status = status;
	}


	public void setId(long id) {
		this.id = id;
	}


	public int getUnidades() {
		return unidades;
	}


	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	
	
}
