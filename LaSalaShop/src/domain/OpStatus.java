package domain;

import java.io.Serializable;

import jpaEx.UserJPA;

public class OpStatus implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String cod;
	private String msg;
	
	
	public OpStatus(){
		cod="KO";
		msg="Error no controlado";
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	};
	
		
}
