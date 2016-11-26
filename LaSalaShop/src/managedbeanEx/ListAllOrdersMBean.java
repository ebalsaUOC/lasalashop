/*
 * @author Alex Cabrera Gil - acabreragil@uoc.edu
 */
package managedbeanEx;

import java.util.*;

import javax.ejb.EJB;
import javax.faces.bean.*;
import javax.naming.Context;
import javax.naming.InitialContext;

import ejbEx.OrderFacadeRemote;
import jpaEx.OrderJPA;


/**
 * Managed Bean ListCompaniesMBean
 */
@ManagedBean(name = "listAllOrders")
@SessionScoped

public class ListAllOrdersMBean {
	
	@EJB private OrderFacadeRemote allOrders;
	protected List<OrderJPA> allOrdersList = new ArrayList<OrderJPA>();
	protected List<OrderJPA> allOrdersListView;
	
	public ListAllOrdersMBean() throws Exception
	{
		this.allOrdersList();
	}

	/**
	 * Method get which return Order Collection
	 * @return Collection
	 */
	public List<OrderJPA> getAllOrdersList()
	{
		return allOrdersList;
	}
	
	/**
	 * Method that takes a collection of instances of OrderJPA calling 
	 * method listAllOrders of the EJB Session
	 * @throws Exception
	 */
	public String allOrdersList() throws Exception
	{
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		allOrders = (OrderFacadeRemote) ctx.lookup("java:app/e-agenda.jar/OrderFacadeBean!ejb.OrderFacadeRemote");
		List<OrderJPA> lista = allOrders.findAllOrders();
		if (lista != null && lista.size() > 0)
			{
			for (OrderJPA e : lista)
				{
				this.allOrdersList.add(e);
				}
			}
		else
			{
			return "errorView";
			}
		return "";
	}
	
	public List<OrderJPA> getAllOrdersListView() throws Exception
		{
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		allOrders = (OrderFacadeRemote) ctx.lookup("java:app/e-agenda.jar/OrderFacadeBean!ejb.OrderFacadeRemote");
		allOrdersListView = (List<OrderJPA>) allOrders.findAllOrders();
		return allOrdersListView;
		}
}