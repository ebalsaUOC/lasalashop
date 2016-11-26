/*
 * @author Alex Cabrera Gil - acabreragil@uoc.edu
 */
package jpaEx;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "eagenda.company")
public class CompanyJPA  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	
	public CompanyJPA(){
		this.id=getId();
	}
	
	public CompanyJPA(String name) {
		super();
		this.name = name;
	}
	
	@GeneratedValue
	@Id
	@Column(name = "COMPANY_ID")
	public int getId() {
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


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
