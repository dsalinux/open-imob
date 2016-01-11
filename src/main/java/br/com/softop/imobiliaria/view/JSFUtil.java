package br.com.softop.imobiliaria.view;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JSFUtil implements Serializable {
    
       public void addMessage(FacesMessage.Severity severity, String message){
        String title = "Erro";
        if(FacesMessage.SEVERITY_INFO.equals(severity)){
            title = "Info";
        } else if(FacesMessage.SEVERITY_WARN.equals(severity)){
            title = "Aviso";
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, title, message));
    }
    
}
