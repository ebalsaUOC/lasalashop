/*
 * @author Alex Cabrera Gil - acabreragil@uoc.edu
 */
package managedbeanEx;

import java.util.*;

import javax.ejb.EJB;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;

import jpaEx.OrderJPA;
import jpaEx.UserJPA;
import ejbEx.OrderFacadeRemote;

/**
 * Managed Bean ListCompaniesMBean
 */
@ManagedBean(name = "findOrdersByUser")
@RequestScoped
public class FindOrdersByUserMBean
	{


	@EJB private OrderFacadeRemote ordersByUser;
	protected List<OrderJPA> ordersByUserList = new ArrayList<OrderJPA>();
	protected List<OrderJPA> ordersByUserListView;
	
	protected UserJPA user;
	
	/*
	 * Getters / Setters
	 */
	
	public void setUser(UserJPA user)
		{
		this.user = user;
		}
	
	public UserJPA getUser()
		{
		return this.user;
		}
	
	/**
	 * constructor
	 * @throws Exception
	 */
	public FindOrdersByUserMBean() throws Exception
		{
		FacesContext facesContext = FacesContext.getCurrentInstance();
	    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		this.setUser((UserJPA) session.getAttribute("user"));
		
		this.ordersByUserList();
		}
	
	/**
	 * Method get which return Order Collection
	 * @return Collection
	 */
	public List<OrderJPA> getOrdersByUserList()
		{
		return ordersByUserList;
		}
	
	/**
	 * Method that takes a collection of instances of OrderJPA calling 
	 * method listAllOrders of the EJB Session
	 * @throws Exception
	 */
	public String ordersByUserList() throws Exception
		{
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		ordersByUser = (OrderFacadeRemote) ctx.lookup("java:app/e-agenda.jar/OrderFacadeBean!ejb.OrderFacadeRemote");
		List<OrderJPA> lista = ordersByUser.findOrdersByUser( this.getUser().getNif() );
		if (lista != null && lista.size() > 0)
			{
			for (OrderJPA e : lista)
				{
				this.ordersByUserList.add(e);
				}
			}
		else
			{
			return "errorView";
			}
		return "";
		}
	
	public List<OrderJPA> getOrdersByUserListView() throws Exception
		{
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		ordersByUser = (OrderFacadeRemote) ctx.lookup("java:app/e-agenda.jar/OrderFacadeBean!ejb.OrderFacadeRemote");
		ordersByUserListView = (List<OrderJPA>) ordersByUser.findOrdersByUser( this.getUser().getNif() );
		return ordersByUserListView;
		}
}