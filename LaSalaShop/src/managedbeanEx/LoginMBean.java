/*
 * 
 * @author ebalsa@uoc.edu
 */
package managedbeanEx;

import java.io.Serializable;
import java.util.*;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import jpaEx.*;
import ejbEx.AdministrationFacadeRemote;
import ejbEx.OpStatus;

/**
 * Managed Bean LoginMBean
 */
@ManagedBean(name = "loginMBean")
@SessionScoped
public class LoginMBean implements Serializable
	{
	private static final long serialVersionUID = 1L;
	@EJB
	private AdministrationFacadeRemote adminRemote;
	protected String email;
	protected String password;
	protected String errMsg;

	public LoginMBean() throws Exception
		{
		this.iniApp();
		}

	/**
	 * Method that takes a collection of instances of CategoryJPA calling method
	 * listAllCategories of the EJB Session
	 * 
	 * @throws Exception
	 */
	public String login(String email, String password) throws Exception
		{
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		adminRemote = (AdministrationFacadeRemote) ctx.lookup("java:app/e-agenda.jar/AdministrationFacadeBean!ejb.AdministrationFacadeRemote");
		OpStatus op = new OpStatus();
		op = adminRemote.login(email, password);
		if ("OK".equals(op.getCod()))
			{
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
			UserJPA sessionUser = adminRemote.getUserForSession(email);
			session.setAttribute("user", sessionUser);
			return "headerUserView";
			}
		else
			{
			this.errMsg = op.getMsg();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.errMsg));
			return "loginView";
			}
		}

	public String gotoRegister()
		{
		return "registerView";
		}

	public AdministrationFacadeRemote getAdminRemote()
		{
		return adminRemote;
		}

	public void setAdminRemote(AdministrationFacadeRemote adminRemote)
		{
		this.adminRemote = adminRemote;
		}

	public static long getSerialversionuid()
		{
		return serialVersionUID;
		}

	public String getEmail()
		{
		return email;
		}

	public void setEmail(String email)
		{
		this.email = email;
		}

	public String getPassword()
		{
		return password;
		}

	public void setPassword(String password)
		{
		this.password = password;
		}

	public String getErrMsg()
		{
		return errMsg;
		}

	public void setErrMsg(String errMsg)
		{
		this.errMsg = errMsg;
		}
	
	public void logout(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		session.setAttribute("user", null);
		session.setAttribute("admin", null);
	}
	
	// Aux method to set initial values for the app
	public void iniApp() throws NamingException
		{
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		adminRemote = (AdministrationFacadeRemote) ctx.lookup("java:app/e-agenda.jar/AdministrationFacadeBean!ejb.AdministrationFacadeRemote");
		adminRemote.iniApp();
		}
	}
