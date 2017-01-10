package br.com.softop.imobiliaria.view;

import br.com.softop.imobiliaria.entity.vo.ContatoVO;
import br.com.softop.imobiliaria.logic.impl.ContatoLogicImpl;
import br.com.softop.imobiliaria.util.exception.BusinessException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.springframework.stereotype.Controller;

@Controller
@ManagedBean
@RequestScoped
public class ContatoView extends JSFUtil{
    
    private ContatoVO contato = new ContatoVO();

    public void enviarMensagem(){
        try {
            new ContatoLogicImpl().enviarMensagem(contato);
            contato = new ContatoVO();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Into", "Enviado com sucesso!"));
        } catch (BusinessException ex) {
            Logger.getLogger(ContatoView.class.getName()).log(Level.SEVERE, null, ex);
            addMessageWarn(ex);
        } catch (Exception ex) {
            Logger.getLogger(ContatoView.class.getName()).log(Level.SEVERE, null, ex);
            addMessageError(ex.getMessage());
        }
    }
    
    public ContatoVO getContato() {
        return contato;
    }
    public void setContato(ContatoVO contato) {
        this.contato = contato;
    }
    
}
