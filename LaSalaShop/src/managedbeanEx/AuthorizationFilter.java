package managedbeanEx;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jpaEx.UserJPA;

@WebFilter("/*")
public class AuthorizationFilter implements Filter
	{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException
		{
		HttpServletRequest req = (HttpServletRequest) request;
		String path = req.getRequestURI();
		boolean error = false;
		if (path.contains("loginView.xhtml") || path.contains("resources/") || path.contains("registerView.xhtml") || path.contains("errorView.xhtml")
				|| path.contains("loginAdminView.xhtml") || path.contains("principal.xhtml") || path.contains("contacte.xhtml"))
			{
			//System.err.println(path + ": no login needed");
			}
		else if ("/e-agenda/".equals(path) || "/e-agenda".equals(path))
			{
			//System.err.println(path + ": home page");
			}
		else
			{
			HttpSession session = req.getSession();
			UserJPA user = (UserJPA) session.getAttribute("user");
			//boolean isAdmin = (session.getAttribute("admin") != null && (int) session.getAttribute("admin") == 1);
			boolean isAdmin=true;
			if (user == null && !isAdmin)
				{
				//System.err.println(path + ": User is not logged in, so redirect to index.");
				error = false;
				}
			else
				{
				//System.err.println(path + ": user loged in");
				}
			}
		if (!error)
			{
			chain.doFilter(request, response);
			}
		else
			{
			HttpServletResponse res = (HttpServletResponse) response;
			res.sendRedirect(req.getContextPath() + "/errorView.xhtml");
			}
		}

	@Override
	public void destroy()
		{
		// TODO Auto-generated method stub
		}

	@Override
	public void init(FilterConfig arg0) throws ServletException
		{
		// TODO Auto-generated method stub
		}
	}