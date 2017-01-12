/*
 * 
 * @author ebalsa@uoc.edu
 */
package managedbean;

import java.io.Serializable;
import java.util.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.naming.NamingException;


/**
 * Managed Bean LoginMBean
 */
@ManagedBean(name = "addEventMBean")
@SessionScoped
public class AddEventMBean implements Serializable
	{
	private static final long serialVersionUID = 1L;
	
	private  String event;
	private Date iniDate;
	private Date endDate; 
	private String space;
	private Collection<String> spacesDescList = new ArrayList<String>();
	
	public AddEventMBean() throws Exception	{
		this.spacesList();
	}

	public String register(String titulo, String autor, String isbn, double precioCompra, 
			double pvp, int unidades) throws Exception{

		return "Prototipo";
	}

	public void spaceValueChanged(ValueChangeEvent spaceChanged){
		
		this.space = spaceChanged.getNewValue().toString();
		
	}
	
	private void spacesList() throws NamingException{

		this.spacesDescList.add("Exposiciones LaSala");
		this.spacesDescList.add("Sala Hygge");
		this.spacesDescList.add("Sala 3");
		
		
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Date getIniDate() {
		return iniDate;
	}

	public void setIniDate(Date iniDate) {
		this.iniDate = iniDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getSpace() {
		return space;
	}

	public void setSpace(String space) {
		this.space = space;
	}

	public Collection<String> getSpacesDescList() {
		return spacesDescList;
	}

	public void setSpacesDescList(Collection<String> spacesDescList) {
		this.spacesDescList = spacesDescList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
