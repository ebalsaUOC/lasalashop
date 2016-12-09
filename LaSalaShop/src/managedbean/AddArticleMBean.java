/*
 * 
 * @author ebalsa@uoc.edu
 */
package managedbean;

import java.io.Serializable;
import java.util.*;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.faces.context.FacesContext;

import jpa.DistributorJPA;


/**
 * Managed Bean LoginMBean
 */
@ManagedBean(name = "addArticleMBean")
@SessionScoped
public class AddArticleMBean implements Serializable
	{
	private static final long serialVersionUID = 1L;
	
	private  String nombreCompra;
	private  String nombreComercial;
	private double netoCompra;
	private double pvp;
	private int unidades;
	private String familia;
	private Collection<String> familiasDescList = new ArrayList<String>();
	
	public AddArticleMBean() throws Exception
		{
		this.familiasDescList();
		}

	public String register(String titulo, String autor, String isbn, double precioCompra, 
			double pvp, int unidades) throws Exception{

		return "Prototipo";
	}

	

	private void familiasDescList() throws NamingException
		{

		this.familiasDescList.add("REGALOS");
		this.familiasDescList.add("ROPA");
		this.familiasDescList.add("POSTERS");
			
	}
	
	public void familiaValueChanged(ValueChangeEvent distribuidoraChanged){
		
		this.familia = distribuidoraChanged.getNewValue().toString();
		
	}

	public String getNombreCompra() {
		return nombreCompra;
	}

	public void setNombreCompra(String nombreVenta) {
		this.nombreCompra = nombreVenta;
	}

	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
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

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public String getFamilia() {
		return familia;
	}

	public void setFamilia(String familia) {
		this.familia = familia;
	}

	public Collection<String> getFamiliasDescList() {
		return familiasDescList;
	}

	public void setFamiliasDescList(Collection<String> familiasDescList) {
		this.familiasDescList = familiasDescList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
