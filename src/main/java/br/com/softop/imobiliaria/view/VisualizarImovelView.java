/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.softop.imobiliaria.view;

import br.com.softop.imobiliaria.entity.Imovel;
import br.com.softop.imobiliaria.entity.vo.ContatoVO;
import br.com.softop.imobiliaria.logic.ImovelLogic;
import br.com.softop.imobiliaria.logic.impl.ContatoLogicImpl;
import br.com.softop.imobiliaria.logic.impl.ImovelLogicImpl;
import br.com.softop.imobiliaria.util.exception.BusinessException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author danilo
 */
//@Component
@RequestScoped
@ManagedBean
public class VisualizarImovelView extends JSFUtil {

    private static final long serialVersionUID = -7732876875307617604L;

    private Imovel entity = null;
    private ContatoVO contato = new ContatoVO();
    private Integer imovelId;

    private ImovelLogic imovelLogic;

    @PostConstruct
    public void init() {
        imovelLogic = new ImovelLogicImpl();
    }

    public void solicitarImovel() {
        try {
            Imovel imv = null;
            if (imovelId != null) {
                imv = imovelLogic.findById(imovelId);
            }
            new ContatoLogicImpl().solicitarImovel(imv, contato);
            addMessage(FacesMessage.SEVERITY_INFO, "Mensagem enviada com sucesso! Aguarde o contato de nossos consultores!");
        } catch (BusinessException ex) {
            addMessage(FacesMessage.SEVERITY_WARN, ex.getMessage());
        } catch (Exception ex) {
            addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
            Logger.getLogger(VisualizarImovelView.class.getName()).log(Level.SEVERE, null, "Nossos servidores estão enfentando algum problema! Tente novamente mais tarde!");

        }
    }

    public void buscarPeloId(Integer id) {
        entity = imovelLogic.findById(id);
    }

    public Imovel getEntity() {
        if (imovelId != null && entity == null) {
            buscarPeloId(imovelId);
        }
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

    public Integer getImovelId() {
        return imovelId;
    }

    public void setImovelId(Integer imovelId) {
        this.imovelId = imovelId;
    }

}
