package uy.org.curso.converters;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import uy.org.curso.domain.BaseEntity;
import uy.org.curso.ejbs.BaseService;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 * Converter base que puede ser utilizado con todas las entities (Si extienden de BaseEntity)
 * @author juan
 *
 */
@Named
@ApplicationScoped
@SuppressWarnings({ "rawtypes", "unchecked" }) 
public class BaseEntityConverter implements Converter {

    @EJB
    private BaseService baseService;

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }

        if (value instanceof BaseEntity) {
            Number id = ((BaseEntity) value).getId();
            return (id != null) ? id.toString() : null;
        } else {
            throw new ConverterException(new FacesMessage(null, "Error al convertir entidad"));
        }
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        try {
            Class<?> type = component.getValueExpression("value").getType(context.getELContext());
            return baseService.find((Class<BaseEntity<? extends Number>>) type, Long.valueOf(value));
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(String.format("%s No es un ID valido para la entity", value)), e);
        }
    }

}