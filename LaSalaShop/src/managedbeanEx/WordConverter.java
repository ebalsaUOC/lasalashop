package managedbeanEx;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import jpaEx.WordJPA;
import ejbEx.AdministrationFacadeRemote;

@ManagedBean(name = "wordConverter")
public class WordConverter implements Converter
	{
	@EJB
	private AdministrationFacadeRemote adminRemote;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value)
		{
		return adminRemote.getWordById(Long.valueOf(value));
		}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value)
		{
		return Integer.toString(((WordJPA) value).getId());
		}
	}