package jpa;
import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

import domain.EnumeratedStatus;

/**
*
* @author ebalsa@uoc.edu
*/

public class ArticlePrototypeJPA implements Serializable {

	private static final long serialVersionUID = 1L;
	

	 private  String commercialName;
	 private double pvp;

	/**
	 * Class constructor methods
	 */
		
	public ArticlePrototypeJPA() {		
		
	}
	
	public ArticlePrototypeJPA(String commercialName, double pvp) {
		super();
		this.commercialName = commercialName;
		this.pvp = pvp;
	}

	public String getCommercialName() {
		return commercialName;
	}

	public void setCommercialName(String commercialName) {
		this.commercialName = commercialName;
	}

	public double getPvp() {
		return pvp;
	}

	public void setPvp(double pvp) {
		this.pvp = pvp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
