/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */

package br.com.kitchiqui.util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.kitchiqui.controller.ClienteMB;
import br.com.kitchiqui.modelo.Cliente;
 
@FacesConverter("clienteConverter")
public class ClienteConverter implements Converter {
 
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                ClienteMB clienteMB = (ClienteMB) fc.getExternalContext().getApplicationMap().get("ClienteMB");
                return clienteMB.getHotListaCliente().get(Integer.parseInt(value));
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problema de filtro", "Inconsistência com o dado"));
            }
        }
        else {
            return null;
        }
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Cliente) object).getId());
        }
        else {
            return null;
        }
    }
}