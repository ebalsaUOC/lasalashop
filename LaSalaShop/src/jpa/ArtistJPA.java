package jpa;

public class ArtistJPA {

	private String name;
	private String surname;
	private String telephone;
	
	public ArtistJPA(String name, String surname, String telephone) {
		super();
		this.name = name;
		this.surname = surname;
		this.telephone = telephone;
	}
		
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


}
