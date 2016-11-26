package managedbeanEx;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import jpaEx.CategoryJPA;
import ejbEx.AdministrationFacadeRemote;

@ManagedBean(name = "categoryConverter")
public class CategoryConverter implements Converter
	{
	@EJB
	private AdministrationFacadeRemote adminRemote;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value)
		{
		return adminRemote.getCategoryById(Long.valueOf(value));
		}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value)
		{
		return ((CategoryJPA) value).getId().toString();
		}
	}