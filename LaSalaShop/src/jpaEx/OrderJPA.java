/*
 * @author Alex Cabrera Gil - acabreragil@uoc.edu
 */
package jpaEx;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "eagenda.order")
public class OrderJPA  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private EventJPA event;
	private UserJPA user;
	private Date date;
	private int numberOfTickets;
	
	public OrderJPA(){
		this.id=getId();
	}
	
	public OrderJPA(EventJPA event, UserJPA user, Date date, int numberOfTickets) {
		super();
		this.event = event;
		this.user = user;
		this.date = date;
		this.numberOfTickets = numberOfTickets;
	}
	
	@GeneratedValue
	@Id
	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	public UserJPA getUser() {
		return user;
	}

	public void setUser(UserJPA user) {
		this.user = user;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "EVENT_ID", nullable = false)
	public EventJPA getEvent() {
		return event;
	}

	public void setEvent(EventJPA event) {
		this.event = event;
	};
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getNumberOfTickets() {
		return numberOfTickets;
	}

	public void setNumberOfTickets(Integer numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
