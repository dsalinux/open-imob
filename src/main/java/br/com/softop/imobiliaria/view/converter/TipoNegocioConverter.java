package br.com.softop.imobiliaria.view.converter;

import br.com.softop.imobiliaria.entity.Imovel;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author danilo
 */
@FacesConverter(value = "tipoNegocioConverter", forClass = Imovel.Tipo.class)  
public class TipoNegocioConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (Imovel.Tipo) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Imovel.Tipo) {
            Imovel.Tipo entity= (Imovel.Tipo) value;
            if (entity != null) {
                uiComponent.getAttributes().put( entity.name(), entity);
                return entity.name();
            }
        }
        return null;
    }
}