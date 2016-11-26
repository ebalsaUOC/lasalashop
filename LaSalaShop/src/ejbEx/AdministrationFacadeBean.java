/*
 * 
 * @author ebalsa@uoc.edu, 2014
 * 
 */
package ejbEx;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jpaEx.AddressJPA;
import jpaEx.CategoryJPA;
import jpaEx.CommentJPA;
import jpaEx.CompanyJPA;
import jpaEx.EventJPA;
import jpaEx.LanguageJPA;
import jpaEx.OrderJPA;
import jpaEx.RattingJPA;
import jpaEx.UserJPA;
import jpaEx.WordJPA;
import ejbEx.AdministrationFacadeRemote;

@Stateless
public class AdministrationFacadeBean implements AdministrationFacadeRemote
	{
	// Persistence Unit Context
	@PersistenceContext(unitName = "eagenda")
	private EntityManager entman;

	public OpStatus addEvent(EventJPA event)
		{
		
		OpStatus op = new OpStatus();
		try
			{
			entman.persist(event);
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
	
	public OpStatus addCategoryToEvent(EventJPA event, CategoryJPA category)
		{
		OpStatus op = new OpStatus();
		System.out.println("[AdministrationFacadeBean].(addCategoryToEvent) Add Category: " + Integer.toString(category.getId()) + " to Event: " + Integer.toString(event.getId()));
		try
			{
			System.out.println("[AdministrationFacadeBean] (addCategoryToEvent) --> Conexion abierta? " + (entman.isOpen()? "Si": "No"));
			if (event.getCategories() == null || event.getCategories().isEmpty())
				{
				System.out.println("[AdministrationFacadeBean] (addCategoryToEvent) la coleccion de categorias es nula o esta vacia");
				}
			event.getCategories().add(category);
			System.out.println("[AdministrationFacadeBean].(addCategoryToEvent) --> event.getCategories().add(category); [ok]");
			entman.merge(event);
			System.out.println("[AdministrationFacadeBean].(addCategoryToEvent) --> entman.merge(event); [ok]");
			category.getEvents().add(event);
			System.out.println("[AdministrationFacadeBean].(addCategoryToEvent) --> category.getEvents().add(event); [ok]");
			entman.merge(category);
			System.out.println("[AdministrationFacadeBean].(addCategoryToEvent) --> entman.merge(category); [ok]");
			op.setCod("OK");			
			return op;
			}
		catch (Exception e)
			{
			op.setCod("KO");
			op.setMsg(e.getMessage());
			System.out.println("[AdministrationFacadeBean ] Error al agregar category al evento " + e.getMessage());
			return op;
			}
		}

	// Modificado por Xesco 04/12/2014
	public OpStatus updateEvent(EventJPA event)
		{
		OpStatus op = new OpStatus();
		if (entman.find(EventJPA.class, event.getId()) == null)
			{
			op.setCod("KO");
			op.setMsg("Unknown Event id");
			return op;
			}
		try
			{
			entman.merge(event);
			op.setCod("OK");
			}
		catch (Exception e)
			{
			op.setCod("KO");
			op.setMsg("Error updating Event with id " + event.getId());
			}
		return op;
		}

	public OpStatus register(UserJPA user)
		{
		OpStatus op = new OpStatus();
		try
			{
			// select email && nif -> return error exists
			String nif = user.getNif();
			String email = user.getEmail();
			Query query = entman.createNativeQuery("SELECT COUNT(u) FROM eagenda.user AS u " + " WHERE EMAIL = '" + email + "' OR USER_ID ='" + nif + "'");
			int count = ((Number) query.getSingleResult()).intValue();
			if (count > 0)
				{
				op.setCod("KO");
				op.setMsg("User exists. Try different nif/email");
				return op;
				}
			// Try to register
			entman.persist(user);
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

	public OpStatus login(String email, String pwd)
		{
		//String par1 = email;
		OpStatus op = new OpStatus();
		try
			{
			email= email.replace("'", "");
			pwd  = pwd.replace("'", "");
			Query query = entman.createNativeQuery("SELECT COUNT(u) " + "FROM eagenda.user AS u" + " WHERE u.email ='" + email + "' AND u.password = '" + pwd + "'");
			int count = ((Number) query.getSingleResult()).intValue();
			if (count == 1)
				{
				op.setCod("OK");
				return op;
				}
			else
				{
				op.setCod("KO");
				op.setMsg("Wrong email/pass combination");
				return op;
				}
			}
		catch (Exception e)
			{
			op.setCod("KO");
			op.setMsg(e.getMessage());
			return op;
			}
		}

	@Override
	public Collection<LanguageJPA> listLanguages()
		{
		@SuppressWarnings("unchecked")
		Collection<LanguageJPA> languages = entman.createQuery("from LanguageJPA").getResultList();
		return languages;
		}

	@Override
	public Collection<CategoryJPA> listCategories()
		{
		System.out.println("[AdministrationFacadeBean] Entro a listCategories");
		@SuppressWarnings("unchecked")
		Collection<CategoryJPA> categories = entman.createQuery("from CategoryJPA").getResultList();
		System.out.println("[AdministrationFacadeBean] listCategories devuelve " + categories.size() + " categorias encontradas");
		return categories;
		}
	
	@Override
	public Set<CompanyJPA> listCompanies()
		{
		@SuppressWarnings("unchecked")
		Set<CompanyJPA> companies = new HashSet<CompanyJPA>(entman.createQuery("from CompanyJPA").getResultList());
		System.out.println("[AdministrationFacadeBean] listCompanies devuelve " + companies.size() + " empresas encontradas");
		return companies;
		}

	@Override
	public OpStatus addAddress(AddressJPA address)
		{
		OpStatus op = new OpStatus();
		try
			{
			entman.persist(address);
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

	@Override
	public void iniApp()
		{
		//Language variables
		LanguageJPA l1 = new LanguageJPA(); 
		LanguageJPA l2 = new LanguageJPA();
		LanguageJPA l3 = new LanguageJPA();
		//Words variables
		WordJPA w1 = new WordJPA();
		WordJPA w2 = new WordJPA();
		WordJPA w3 = new WordJPA();
		WordJPA w4 = new WordJPA();
		WordJPA w5 = new WordJPA();
		//User variables
		UserJPA u1 = new UserJPA();
		UserJPA u2 = new UserJPA();
		UserJPA u3 = new UserJPA();
		UserJPA u4 = new UserJPA();
		UserJPA u5 = new UserJPA();
		//AddressJPA variables
		AddressJPA a1 = new AddressJPA();
		AddressJPA a2 = new AddressJPA();
		AddressJPA a3 = new AddressJPA();
		AddressJPA a4 = new AddressJPA();
		AddressJPA a5 = new AddressJPA();
		AddressJPA a6 = new AddressJPA();
		AddressJPA a7 = new AddressJPA();
		AddressJPA a8 = new AddressJPA();		
		//CategoryJPA variables
		CategoryJPA c1 = new CategoryJPA();
		CategoryJPA c2 = new CategoryJPA();
		CategoryJPA c3 = new CategoryJPA();
		CategoryJPA c4 = new CategoryJPA();
		//CompanyJPA variables
		CompanyJPA co1 = new CompanyJPA();
		CompanyJPA co2 = new CompanyJPA();
		CompanyJPA co3 = new CompanyJPA();
		CompanyJPA co4 = new CompanyJPA();
		//EventJPA variables
		EventJPA e1 = new EventJPA();
		EventJPA e2 = new EventJPA();
		EventJPA e3 = new EventJPA();
		EventJPA e4 = new EventJPA();
		//RattingJPA variables
		RattingJPA r1 = new RattingJPA();
		RattingJPA r2 = new RattingJPA();
		RattingJPA r3 = new RattingJPA();
		RattingJPA r4 = new RattingJPA();
		RattingJPA r5 = new RattingJPA();
		//CommentJPA variables
		CommentJPA cm1 = new CommentJPA();
		CommentJPA cm2 = new CommentJPA();
		CommentJPA cm3 = new CommentJPA();
		//OrderJPA variables
		OrderJPA o1 = new OrderJPA();
		OrderJPA o2 = new OrderJPA();
		OrderJPA o3 = new OrderJPA();
		OrderJPA o4 = new OrderJPA();
		OrderJPA o5 = new OrderJPA();	
		OrderJPA o6 = new OrderJPA();	
		
		// 1-Ini languages
		@SuppressWarnings("unchecked")
		Collection<CompanyJPA> lanList = entman.createQuery("from LanguageJPA").getResultList();
		if (lanList == null || lanList.size() < 1)
			{
			//	LanguageJPA l1
			l1.setCode("ESP");
			l1.setISOcode("ESP");
			l1.setDescription("CASTELLANO");
			entman.persist(l1);
			//	LanguageJPA l2
			l2.setCode("CAT");
			l2.setISOcode("CAT");
			l2.setDescription("CATALA");
			entman.persist(l2);
			//	LanguageJPA l3
			l3.setCode("ENG");
			l3.setISOcode("ENG");
			l3.setDescription("ENGLISH");
			entman.persist(l3);
			}
		// 2-Ini words
		@SuppressWarnings("unchecked")
		Collection<WordJPA> wordList = entman.createQuery("from WordJPA").getResultList();
		if (wordList == null || wordList.size() < 1)
			{
			// WordJPA w1
			w1.setTag("SOPAR");
			entman.persist(w1);
			//WordJPA w2
			w2.setTag("CONCERT");
			entman.persist(w2);
			//WordJPA w3
			w3.setTag("CATA");
			entman.persist(w3);
			//WordJPA w4
			w4.setTag("CONFERENCIA");
			entman.persist(w4);			
			}
		// 3-Ini address
		@SuppressWarnings("unchecked")
		Collection<AddressJPA> addressList = entman.createQuery("from AddressJPA").getResultList();
		if (addressList == null || addressList.size() < 1)
			{
			//AddressJPA a1
			a1.setStreet("Psg. de Gracia");
			a1.setNumber("1");
			a1.setCity("Barcelona");
			a1.setCountry("Espanya");
			entman.persist(a1);
			//AddressJPA a2
			a2.setStreet("Diagonal");
			a2.setNumber("100");
			a2.setCity("Barcelona");
			a2.setCountry("Espanya");
			entman.persist(a2);
			//AddressJPA a3
			a3.setStreet("Pza. del Pilar");
			a3.setNumber("1");
			a3.setCity("Zaragoza");
			a3.setCountry("Espanya");
			entman.persist(a3);
			//AddressJPA a4
			a4.setStreet("Gran Via");
			a4.setNumber("9");
			a4.setCity("Barcelona");
			a4.setCountry("Espanya");
			entman.persist(a4);
			//AddressJPA a5
			a5.setStreet("Major");
			a5.setNumber("10");
			a5.setCity("Reus");
			a5.setCountry("Espanya");
			entman.persist(a5);
			//AddressJPA a6
			a6.setStreet("Camp Nou");
			a6.setNumber("");
			a6.setCity("Barcelona");
			a6.setCountry("Espanya");
			entman.persist(a6);
			//AddressJPA a7
			a7.setStreet("Edifici UOC 22@");
			a7.setNumber("5");
			a7.setCity("Barcelona");
			a7.setCountry("Espanya");
			entman.persist(a7);
			//AddressJPA a8
			a8.setStreet("Rambles");
			a8.setNumber("50");
			a8.setCity("Barcelona");
			a8.setCountry("Espanya");
			entman.persist(a8);			
			}
		// 3-Ini users
		@SuppressWarnings("unchecked")
		Collection<UserJPA> userList = entman.createQuery("from UserJPA").getResultList();
		if (userList == null || userList.size() < 1)
			{
			//UserJPA u1
			u1.setNif("11111111A");
			u1.setName("Marc");
			u1.setSurname("Colom Royo");
			u1.setPreferedLanguage(l2);							
			u1.setAddress(a1);
			u1.setPassword("1234");
			u1.setEmail("mcolomro@uoc.edu");
			entman.persist(u1);
			//UserJPA u2
			u2.setNif("22222222B");
			u2.setName("Xesco");
			u2.setSurname("Alarcon");
			u2.setPreferedLanguage(l2);							
			u2.setAddress(a2);
			u2.setPassword("1234");
			u2.setEmail("falarconm@uoc.edu");
			entman.persist(u2);
			//UserJPA u3
			u3.setNif("33333333C");
			u3.setName("Edu");
			u3.setSurname("Balsa");
			u3.setPreferedLanguage(l1);							
			u3.setAddress(a3);
			u3.setPassword("1234");
			u3.setEmail("ebalsa@uoc.edu");
			entman.persist(u3);
			//UserJPA u4
			u4.setNif("44444444D");
			u4.setName("Alex");
			u4.setSurname("Cabrera");
			u4.setPreferedLanguage(l2);							
			u4.setAddress(a4);
			u4.setPassword("1234");
			u4.setEmail("acabreragil@uoc.edu");
			entman.persist(u4);
			//UserJPA u5
			u5.setNif("55555555E");
			u5.setName("Clara");
			u5.setSurname("Dominguez");
			u5.setPreferedLanguage(l2);							
			u5.setAddress(a5);
			u5.setPassword("1234");
			u5.setEmail("cdominguezna@uoc.edu");
			entman.persist(u5);
			}
		// 4-Ini categories
		@SuppressWarnings("unchecked")
		Collection<CategoryJPA> categoryList = entman.createQuery("from CategoryJPA").getResultList();
		if (categoryList == null || categoryList.size() < 1)
			{
			//CategoryJPA c1
			c1.setName("Sopars");
			c1.setDescription("Sopars Colloquis");
			entman.persist(c1);
			//CategoryJPA c2
			c2.setName("Concerts");
			c2.setDescription("Concerts Musicals");
			entman.persist(c2);
			//CategoryJPA c3
			c3.setName("Cata");
			c3.setDescription("Cata en Bodegues");
			entman.persist(c3);
			//CategoryJPA c4
			c4.setName("Conferncies");
			c4.setDescription("Conferncies Divulgatives");
			entman.persist(c4);
			}
		// 5-Ini companies
		@SuppressWarnings("unchecked")
		Collection<CompanyJPA> companyList = entman.createQuery("from CompanyJPA").getResultList();
		if (companyList == null || companyList.size() < 1)
			{
			//CompanyJPA co1
			co1.setName("UOC");
			entman.persist(co1);
			//CompanyJPA co2
			co2.setName("FC Barcelona");
			entman.persist(co2);
			//CompanyJPA co3
			co3.setName("Bodegues Penedes");
			entman.persist(co3);
			//CompanyJPA co4
			co4.setName("Teatre Nacional de Catalunya");
			entman.persist(co4);
			}
		// 6-Ini events
		@SuppressWarnings("unchecked")
		Collection<EventJPA> eventList = entman.createQuery("from EventJPA").getResultList();
		if (eventList == null || eventList.size() < 1)			
			{ try 
			{
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY MM DD");
			
			//EventJPA e1
			e1.setName("Conferncia PDP");			
			e1.setDescription("a");
			e1.setPicture("event4.png");
			e1.setLocation(a7);				
				Date dateI = sdf.parse("2015 04 15");
			e1.setIniDate(dateI);
				Date dateE = sdf.parse("2015 04 15");
			e1.setEndDate(dateE);	
			e1.setRatting(5);
			e1.setCompany(co1);
			entman.persist(e1);			
			//EventJPA e2
			e2.setName("Concert Rollings Stones");
			e2.setDescription("Concert Rollings Stones. Gira 2015");
			e2.setPicture("event2.png");
			e2.setLocation(a6);				
				dateI = sdf.parse("2015 01 31");
			e2.setIniDate(dateI);
				dateE = sdf.parse("2015 01 31");
			e2.setEndDate(dateE);
			e2.setRatting(5);
			e2.setCompany(co2);
			entman.persist(e2);
			//EventJPA e3
			e3.setName("Sopar Premis Gaudi");
			e3.setDescription("Sopar Premis Gaudi Edici015");
			e3.setPicture("event1.png");
			e3.setLocation(a8);				
				dateI = sdf.parse("2015 02 19");
			e3.setIniDate(dateI);
				dateE = sdf.parse("2015 02 19");
			e3.setEndDate(dateE);		
			e3.setRatting(5);
			e3.setCompany(co4);
			entman.persist(e3);
			//EventJPA e4
			e4.setName("Concert Jack Johnson");
			e4.setDescription("Concert Jack Johnson Gira 2015");
			e4.setPicture("event2.png");
			e4.setLocation(a6);				
				dateI = sdf.parse("2015 05 19");
			e4.setIniDate(dateI);
				dateE = sdf.parse("2015 05 19");
			e4.setEndDate(dateE);	
			e4.setRatting(5);
			e4.setCompany(co2);
			entman.persist(e4);			
			} catch (ParseException e) {}			
			}		
		// 7-Ini rattings
		@SuppressWarnings("unchecked")
		Collection<RattingJPA> rattingList = entman.createQuery("from RattingJPA").getResultList();
		if (rattingList == null || rattingList.size() < 1)
			{
			//RattingJPA r1
			r1.setEvent(e1);
			r1.setUser(u1);
			r1.setRatting(9);			
			entman.persist(r1);
			//RattingJPA r2
			r2.setEvent(e2);
			r2.setUser(u2);
			r2.setRatting(8);
			entman.persist(r2);
			//RattingJPA r3
			r3.setEvent(e3);
			r3.setUser(u3);
			r3.setRatting(7);
			entman.persist(r3);
			//RattingJPA r4
			r4.setEvent(e4);
			r4.setUser(u4);
			r4.setRatting(9);
			entman.persist(r4);
			//RattingJPA r5
			r5.setEvent(e1);
			r5.setUser(u5);
			r5.setRatting(5);
			entman.persist(r5);			
			}
		// 8-Ini comments
		@SuppressWarnings("unchecked")
		Collection<CommentJPA> commentList = entman.createQuery("from CommentJPA").getResultList();
		if (commentList == null || commentList.size() < 1)
			{
			//CommentJPA cm1
			cm1.setEvent(e1);
			cm1.setUser(u1);
			cm1.setText("Molt aconsellable pels futurs estudiants d'aquesta assignatura. Et divertiras amb les anecdotes d'altres estudiants");
			entman.persist(cm1);
			//CommentJPA cm2
			cm2.setEvent(e1);
			cm2.setUser(u2);
			cm2.setText("Molt divertit");
			entman.persist(cm2);
			//CommentJPA cm3
			cm3.setEvent(e2);
			cm3.setUser(u2);
			cm3.setText("");
			entman.persist(cm3);			
			}
		// 8-Ini orders
		@SuppressWarnings("unchecked")
		Collection<OrderJPA> orderList = entman.createQuery("from OrderJPA").getResultList();		
		if (orderList == null || orderList.size() < 1)			
			{ try
			{
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY MM DD");
			Date dateO = null;
			//OrderJPA o1
			o1.setEvent(e1);
			o1.setUser(u1);
			o1.setNumberOfTickets(4);
				dateO = sdf.parse("2015 04 15");			
			o1.setDate(dateO);
			entman.persist(o1);
			//OrderJPA o2
			o2.setEvent(e1);
			o2.setUser(u2);
			o2.setNumberOfTickets(2);
				dateO = sdf.parse("2015 04 15");			
			o2.setDate(dateO);
			entman.persist(o2);
			//OrderJPA o3
			o3.setEvent(e2);
			o3.setUser(u2);
			o3.setNumberOfTickets(6);
				dateO = sdf.parse("2015 01 31");			
			o3.setDate(dateO);
			entman.persist(o3);
			//OrderJPA o4
			o4.setEvent(e3);
			o4.setUser(u3);
			o4.setNumberOfTickets(2);
				dateO = sdf.parse("2015 02 19");			
			o4.setDate(dateO);
			entman.persist(o4);			
			//OrderJPA o5
			o5.setEvent(e3);
			o5.setUser(u4);
			o5.setNumberOfTickets(2);
				dateO = sdf.parse("2015 02 19");			
			o5.setDate(dateO);
			entman.persist(o5);
			//OrderJPA o6
			o6.setEvent(e3);
			o6.setUser(u5);
			o6.setNumberOfTickets(2);
				dateO = sdf.parse("2015 02 19");			
			o6.setDate(dateO);
			entman.persist(o6);
			
			}catch (ParseException e) {}			
			}		


		}

	@Override
	public Object getCategoryById(Long valueOf)
		{
		// Find Category
		System.out.println("Buscamos category por id " + Long.toString(valueOf));
		CategoryJPA categoryFetch = (CategoryJPA) entman.createQuery("FROM CategoryJPA c WHERE c.id = :id").setParameter("id", valueOf.intValue()).getSingleResult();
		if (categoryFetch != null)
			{
			System.out.println("Hemos encontrado la category llamada " + categoryFetch.getName());
			}
		else
			{
			System.out.println("No hemos encontrado la category");
			}
		return categoryFetch;
		}

	@Override
	public Set<WordJPA> getAllWords()
		{
		@SuppressWarnings("unchecked")
		Set<WordJPA> allWords = new HashSet<WordJPA>(entman.createQuery("from WordJPA").getResultList());
		return allWords;
		}

	@Override
	public Object getWordById(Long valueOf)
		{
		System.out.println("Buscamos keyword por id " + Long.toString(valueOf));
		WordJPA wordFetch = (WordJPA) entman.createQuery("FROM WordJPA w WHERE w.id = :id").setParameter("id", valueOf.intValue()).getSingleResult();
		if (wordFetch != null)
			{
			System.out.println("Hemos encontrado la keyword llamada " + wordFetch.getTag());
			}
		else
			{
			System.out.println("No hemos encontrado la keyword");
			}
		return wordFetch;
		}
	
	@Override
	public Object getCompanyById(Long valueOf)
		{
		CompanyJPA companyFetch = (CompanyJPA) entman.createQuery("FROM CompanyJPA w WHERE w.id = :id").setParameter("id", valueOf.intValue()).getSingleResult();
		if (companyFetch != null)
			{
			//System.out.println("Hemos encontrado la CompanyJPA llamada " + companyFetch.getName());
			}
		else
			{
			//System.out.println("No hemos encontrado la CompanyJPA");
			}
		return companyFetch;
		}
	

	public UserJPA getUserForSession(String email)
		{
		// Obtener el user para guardar la sesion
		UserJPA user = (UserJPA) entman.createQuery("from UserJPA u where u.email = '" + email + "'").getSingleResult();
		return user;
		}
	}
