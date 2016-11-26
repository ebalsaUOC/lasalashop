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
@Table(name = "eagenda.comment")
public class CommentJPA implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String text;
	private UserJPA user;
	private EventJPA event;
	
	public CommentJPA(){
		this.id=getId();
	}

	public CommentJPA(String text, UserJPA user, EventJPA event) {
		super();
		this.text = text;
		this.user = user;
		this.event = event;
	}

	@Id
	@GeneratedValue
	@Column(name="COMMENT_ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	};
	
	
}
