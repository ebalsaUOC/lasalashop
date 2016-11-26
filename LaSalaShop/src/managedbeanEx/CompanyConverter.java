package managedbeanEx;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import ejbEx.AdministrationFacadeRemote;

@ManagedBean(name = "companyConverter")
public class CompanyConverter implements Converter
	{
	@EJB
	private AdministrationFacadeRemote adminRemote;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value)
		{
		return Integer.parseInt(value);
		}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value)
		{
		return value+"";
		}
	}