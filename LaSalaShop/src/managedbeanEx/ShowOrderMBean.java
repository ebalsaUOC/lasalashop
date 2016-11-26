/*
 * @author Alex Cabrera Gil - acabreragil@uoc.edu
 */
package managedbeanEx;

import java.io.Serializable;
import java.util.Properties;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;

import jpaEx.EventJPA;
import jpaEx.UserJPA;
import ejbEx.OpStatus;
import ejbEx.OrderFacadeRemote;


/**
 * Managed Bean ShowOrderMBean
 */
@ManagedBean(name = "showOrder")
@SessionScoped
public class ShowOrderMBean implements Serializable
	{
	
	private static final long serialVersionUID = 1L;

	@EJB
	private OrderFacadeRemote orderRemote;
	protected UserJPA user;
	protected String errMsg;
	protected EventJPA event;
	protected String numberOfTickets;
	
	
	public ShowOrderMBean() throws Exception	
	 	{
	
	 	}
	
	
	/*
	 * Getters / Setters
	 */
	
	public UserJPA getUser()
		{
		return this.user;
		}
	public void setUser(UserJPA user)
		{
		this.user = user;
		}
	
	public EventJPA getEvent()
		{
		return this.event;
		}
	public void setEvent(EventJPA event)
		{
		this.event = event;
		}
	
	public String getNumberOfTickets()
		{
		return this.numberOfTickets;
		}
	public void setNumberOfTickets(String numberOfTickets)
		{
		this.numberOfTickets = numberOfTickets;
		}
	
	/**
	 * inicialitza les dades per a fer la compra
	 * @param event referencia del event que es vol comprar
	 * @return la pantalla de destí
	 */
	public String show(EventJPA event)
		{
		this.setEvent(event);
		this.setNumberOfTickets("1");
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
	    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		this.setUser((UserJPA) session.getAttribute("user"));
		
		return "orderDetailView";
		}

	
	/**
	 * realitza una nova compra
	 * @return un missatge amb l'estat de la petició
	 */
	public String buy() throws Exception 
		{	
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		orderRemote = (OrderFacadeRemote)ctx.lookup("java:app/e-agenda.jar/OrderFacadeBean!ejb.OrderFacadeRemote"); 
		
		OpStatus op = new OpStatus();
		
		if(this.numberOfTickets.length()>3)
			{
			this.numberOfTickets=this.numberOfTickets.substring(0,3);
			}
		
		if(this.numberOfTickets==null || !isNumeric(this.numberOfTickets) || 
				Integer.valueOf(this.numberOfTickets)<1 || Integer.valueOf(this.numberOfTickets)>10 )
			{
			this.errMsg = "Valor incorrecte. El nombre ha d'estar compres entre 0 i 10";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.errMsg));
			return "orderDetailView";
			}
		
		op = orderRemote.order(this.event, this.user, Integer.valueOf(this.numberOfTickets));
		
		if("OK".equals(op.getCod())) 
			return "ordersUserListView";
		else
			{
			this.errMsg = op.getMsg();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.errMsg));
			return "orderDetailView";
			}
		}
	
	public boolean isNumeric(String s) 
		{  
		return s.matches("[-+]?\\d*\\.?\\d+");  
		}  
	}