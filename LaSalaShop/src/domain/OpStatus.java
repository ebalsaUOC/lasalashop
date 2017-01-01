package domain;

import java.io.Serializable;

public class OpStatus implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String cod;
	private String msg;
	
	
	public OpStatus(){
		cod="KO";
		msg="Unknown error";
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
	}
	
		
}
