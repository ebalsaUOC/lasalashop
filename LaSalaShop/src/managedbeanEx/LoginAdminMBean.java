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

import ejbEx.AdministrationFacadeRemote;

/**
 * Managed Bean LoginMBean
 */
@ManagedBean(name = "loginAdminMBean")
@SessionScoped
public class LoginAdminMBean implements Serializable
	{
	private static final long serialVersionUID = 1L;
	private static final String ADMIN_PASSWORD = "fatos";
	protected String errMsg;
	@EJB
	private AdministrationFacadeRemote adminRemote;

	public LoginAdminMBean() throws Exception
		{
		this.errMsg = "You are not allowed to access into the realm of Gods";
		this.iniApp();
		}

	public String login(String password) throws Exception
		{
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		if (ADMIN_PASSWORD.equals(password.trim()))
			{
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
			session.setAttribute("admin", 1);
			return "headerView";
			}
		else
			this.errMsg = "Identificacion erronea";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.errMsg));
			return "loginAdminView";
		}

	public static long getSerialversionuid()
		{
		return serialVersionUID;
		}

	public String getErrMsg()
		{
		return errMsg;
		}

	public void setErrMsg(String errMsg)
		{
		this.errMsg = errMsg;
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
