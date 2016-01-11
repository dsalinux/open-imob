/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.softop.imobiliaria.view;

import br.com.softop.imobiliaria.entity.Imovel;
import br.com.softop.imobiliaria.entity.vo.ContatoVO;
import br.com.softop.imobiliaria.logic.impl.ContatoLogicImpl;
import br.com.softop.imobiliaria.logic.impl.ImovelLogicImpl;
import br.com.softop.imobiliaria.util.exception.BusinessException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author danilo
 */
@RequestScoped
@ManagedBean
public class VisualizarImovelView extends JSFUtil {

    private Imovel entity = null;
    private ContatoVO contato = new ContatoVO();

    @PostConstruct
    public void init() {
        try {
            Integer id;
            id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("imovel_id"));
            if (id != null) {
                buscarPeloId(id);
            }
        } catch (NumberFormatException ex) {
        }
    }

    public void solicitarImovel() {
        try {
            try {
                Integer id;
                id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("imovel_id"));
                if (id != null) {
                    buscarPeloId(id);
                }
            } catch (NumberFormatException ex) {
            }
            new ContatoLogicImpl().solicitarImovel(entity, contato);
            addMessage(FacesMessage.SEVERITY_INFO, "Mensagem enviada com sucesso! Aguarde o contato de nossos consultores!");
        } catch (BusinessException ex) {
            addMessage(FacesMessage.SEVERITY_WARN, ex.getMessage());
        } catch (Exception ex) {
            addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
            Logger.getLogger(VisualizarImovelView.class.getName()).log(Level.SEVERE, null, "Nossos servidores estão enfentando algum problema! Tente novamente mais tarde!");

        }
    }

    public void buscarPeloId(Integer id) {
        entity = new ImovelLogicImpl().findById(id);
    }

    public Imovel getEntity() {
        return entity;
    }

    public void setEntity(Imovel entity) {
        this.entity = entity;
    }

    public ContatoVO getContato() {
        return contato;
    }

    public void setContato(ContatoVO contato) {
        this.contato = contato;
    }

}
