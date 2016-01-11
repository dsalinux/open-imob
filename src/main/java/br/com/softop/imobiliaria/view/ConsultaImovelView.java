package br.com.softop.imobiliaria.view;

import br.com.softop.imobiliaria.entity.Imovel;
import br.com.softop.imobiliaria.entity.vo.ImovelVO;
import br.com.softop.imobiliaria.util.Context;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author danilo
 */
@ManagedBean
@RequestScoped
public class ConsultaImovelView extends JSFUtil{

    private ImovelVO imovel;

    public ConsultaImovelView() {
        imovel = new ImovelVO();
        imovel.setTipo_(Imovel.Tipo.ALUGUEL);
    }

    public void consultar() {
        Context.setImovelConsulta(imovel);
        try {
            if (Imovel.Tipo.ALUGUEL.equals(imovel.getTipo_())) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("alugar.jsf");
            } else {
                FacesContext.getCurrentInstance().getExternalContext().redirect("comprar.jsf");
            }
        } catch (IOException ex) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Estamos enfrentando alguns problemas! Tente novamente!");
            Logger.getLogger(ConsultaImovelView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ImovelVO getImovel() {
        return imovel;
    }

    public void setImovel(ImovelVO imovel) {
        this.imovel = imovel;
    }
}
