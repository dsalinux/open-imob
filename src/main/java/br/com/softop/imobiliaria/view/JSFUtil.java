package br.com.softop.imobiliaria.view;

import br.com.softop.imobiliaria.util.exception.BusinessException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JSFUtil implements Serializable {

    public void addMessage(FacesMessage.Severity severity, String message) {
        String title = "Erro";
        if (FacesMessage.SEVERITY_INFO.equals(severity)) {
            title = "Info";
        } else if (FacesMessage.SEVERITY_WARN.equals(severity)) {
            title = "Aviso";
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, title, message));
    }
    
    public void addMessageInfo(String message){
        addMessage(FacesMessage.SEVERITY_INFO, message);
    }
    public void addMessageWarn(String message){
        addMessage(FacesMessage.SEVERITY_WARN, message);
    }
    public void addMessageWarn(BusinessException be){
        addMessageWarn(be.getMessage());
    }
    public void addMessageError(String message){
        addMessage(FacesMessage.SEVERITY_ERROR, message);
    }
    public void addMessageFatal(String message){
        addMessage(FacesMessage.SEVERITY_FATAL, message);
    }

}
