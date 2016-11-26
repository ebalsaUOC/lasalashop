/*
 * @author Alex Cabrera Gil - acabreragil@uoc.edu
 */
package ejbEx;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jpaEx.EventJPA;
import jpaEx.OrderJPA;
import jpaEx.UserJPA;



@Stateless
public class OrderFacadeBean implements OrderFacadeRemote
	{
	
	// Persistence Unit Context
	@PersistenceContext(unitName = "eagenda")
	private EntityManager entman;
	
	/**
	 * busca les compres fetes per un usuari
	 * @param nif identificador de l'usuari que està realitzant la cerca
	 * @return una llista amb totes les ordres de l'usuari
	 */
	@Override
	public List<OrderJPA> findOrdersByUser(String nif) 
		{
		@SuppressWarnings("unchecked")
		List<OrderJPA> userOrders = entman.createQuery("from OrderJPA where user_id='"+nif+"' order by Id").getResultList();
		
		return userOrders;
		}
	
	/**
	 * busca totes les compres fetes al sistema
	 * @return una llista amb totes les ordres del sistema
	 */
	@Override
	public List<OrderJPA> findAllOrders() 
		{
		@SuppressWarnings("unchecked")
		List<OrderJPA> allOrders = entman.createQuery("from OrderJPA order by Id").getResultList();
		
		return allOrders;
		}

	/**
	 * busca una compra en concret
	 * @param id identificacor de la compra
	 * @return una referència a la compra que es vol mostrar
	 */
	@Override
	public OrderJPA showOrder(int id) 
		{
		OrderJPA order = entman.find(OrderJPA.class, id);
		return order;
		}

	/**
	 * realitza una nova compra
	 * @param event representació de l'event que es vl comprar
	 * @param user representació de l'usuari que està realitzant la compra
	 * @param numberOfTickets nombre de localitats que s'han de comprar
	 * @return un missatge amb l'estat de la petició
	 */
	@Override
	public OpStatus order(EventJPA event, UserJPA user, int numberOfTickets) 
		{
		OpStatus op= new OpStatus();
		
		//Find User
		UserJPA userFetch = (UserJPA) entman.createQuery("FROM UserJPA u  WHERE u.nif = :nif").setParameter("nif", user.getNif()).getSingleResult();
		if(userFetch == null)
			{
			op.setCod("KO");
			op.setMsg("User not found");
			return op;
			}
		
		//Find Event
		EventJPA eventFetch = (EventJPA) entman.createQuery("FROM EventJPA e WHERE e.id = :id").setParameter("id", event.getId()).getSingleResult();
		if(eventFetch == null)
			{
			op.setCod("KO");
			op.setMsg("Event not found");
			return op;
			}
		
		
		//Add ratting & persists
		OrderJPA order = new OrderJPA(eventFetch, userFetch, new Date(), numberOfTickets);
		
		try
			{
			entman.persist(order);
			op.setCod("OK");
			return op;
			} 
		catch (Exception e) 
			{
			op.setCod("KO");
			op.setMsg(e.getMessage());
			return op;
			}
			
		}
	}
