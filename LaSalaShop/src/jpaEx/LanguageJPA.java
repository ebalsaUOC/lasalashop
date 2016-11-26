package jpaEx;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "eagenda.language")
public class LanguageJPA implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String code;
	private String description;
	private String ISOcode;
	
	public LanguageJPA(){
		this.id=getId();
	}

	public LanguageJPA(String code, String description, String iSOcode) {
		super();
		this.code = code;
		this.description = description;
		ISOcode = iSOcode;
	}

	@Id
	@GeneratedValue
	@Column(name="LANGUAGE_ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getISOcode() {
		return ISOcode;
	}

	public void setISOcode(String iSOcode) {
		ISOcode = iSOcode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	};

	
	
}
