package br.com.softop.imobiliaria.view;

import br.com.softop.imobiliaria.entity.Inquilino;
import br.com.softop.imobiliaria.logic.impl.InquilinoLogicImpl;
import br.com.softop.imobiliaria.util.exception.BusinessException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author danilo
 */
@ManagedBean
@RequestScoped
public class InquilinoSiteView extends JSFUtil {
    
    private Inquilino inquilino = new Inquilino();
    
    public void salvar(){
        try {
            new InquilinoLogicImpl().save(inquilino);
            inquilino = new Inquilino();
            addMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso! Aguarde o contato de nossos consultores.");
        } catch (BusinessException ex) {
            addMessage(FacesMessage.SEVERITY_WARN, ex.getMessage());
        } catch (Exception ex){
            addMessage(FacesMessage.SEVERITY_ERROR, "Nossos servidores estão enfrentando algum problema! Por favor tente novamente!");
        }
    }

    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }
    
    
}
