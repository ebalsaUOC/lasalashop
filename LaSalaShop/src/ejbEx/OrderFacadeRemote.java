/*
 * @author Alex Cabrera Gil - acabreragil@uoc.edu
 */
package ejbEx;

import java.util.List;

import javax.ejb.Remote;

import jpaEx.EventJPA;
import jpaEx.OrderJPA;
import jpaEx.UserJPA;

/**
 * Session EJB Remote Interfaces
 */
@Remote
public interface OrderFacadeRemote
	{
	/**
	 * Remotely invoked method.
	 */
	
	public List<OrderJPA> findOrdersByUser(String nif);
	
	public List<OrderJPA> findAllOrders();
	
	public OrderJPA showOrder(int id);
	
	public OpStatus order(EventJPA event, UserJPA user, int numberOfTickets);
	
	}
