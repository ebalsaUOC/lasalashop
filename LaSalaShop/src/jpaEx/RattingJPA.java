package jpaEx;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "eagenda.ratting")
public class RattingJPA implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private float ratting;
	private UserJPA user;
	private EventJPA event;
	
	public RattingJPA(){
		this.id=getId();
	}

	public RattingJPA(int ratting, UserJPA user, EventJPA event) {
		super();
		this.ratting = ratting;
		this.user = user;
		this.event = event;
	}
	
	public RattingJPA(float ratting, EventJPA event) {
		super();
		this.ratting = ratting;
		this.event = event;
	}

	@Id
	@GeneratedValue
	@Column(name="RATTING_ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getRatting() {
		return ratting;
	}

	public void setRatting(float ratting) {
		this.ratting = ratting;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
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
}
