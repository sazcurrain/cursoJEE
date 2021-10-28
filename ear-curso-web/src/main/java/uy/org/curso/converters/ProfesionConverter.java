package uy.org.curso.converters;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import uy.org.curso.domain.Profesion;
import uy.org.curso.ejbs.ProfesionService;

/**
 * Converter para la entidad Profesion
 * Si bien no se usa (se esta usando BaseEntityConverter) queda como ejemplo
 * @author juan
 *
 */
@Deprecated
@Named
@RequestScoped
public class ProfesionConverter implements Converter {
	
	@EJB
	private ProfesionService profesionService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || "".equals(value.trim())) {
			return null;
		} 
		
		Profesion profesion = profesionService.find(Long.valueOf(value));
		
		return profesion;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value instanceof Profesion)
		{
			Profesion p = (Profesion) value;
            return String.valueOf(p.getId());
		}
		else
		{
			return "";
		}
	}
}
