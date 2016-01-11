package br.com.softop.imobiliaria.view;

import br.com.softop.imobiliaria.entity.Banner;
import br.com.softop.imobiliaria.entity.Login;
import br.com.softop.imobiliaria.logic.impl.BannerLogicImpl;
import br.com.softop.imobiliaria.util.Context;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * @author danilo
 */
@RequestScoped
@ManagedBean
public class ContextView extends JSFUtil {

    private Integer idImovel;
    
    public String getMensagemLogado() {
        String mensagem = "";
        if (Context.getLogin() != null) {
            int hora = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            if (hora >= 0 && hora < 12) {
                mensagem = "Bom dia ";
            } else if (hora >= 12 && hora < 18) {
                mensagem = "Boa tarde ";
            } else {
                mensagem = "Boa noite ";
            }
            mensagem += Context.getLogin().getLogin();
        }
        return mensagem;
    }

    public Login getLogin() {
        return Context.getLogin();
    }

    public void setLogin(Login login) {
        Context.setLogin(login);
    }

    public void deslogar() {
        Context.setLogin(null);
    }

    public List<Banner> getBanners() {
        return new BannerLogicImpl().buscarAtivos();
    }
    
    public boolean isPesquisaDaHome(){
        return Context.getImovelConsulta() != null;
    }

    public Integer getIdImovel() {
        return idImovel;
    }

    public void setIdImovel(Integer idImovel) {
        this.idImovel = idImovel;
    }
    
}
