package br.com.softop.imobiliaria.view;

import br.com.softop.imobiliaria.entity.Configuracoes;
import br.com.softop.imobiliaria.logic.ConfiguracoesLogic;
import br.com.softop.imobiliaria.logic.impl.ConfiguracoesLogicImpl;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author danilo
 */
//@Controller
@ManagedBean
@ApplicationScoped
public class ConfiguracoesView extends JSFUtil {

    public Configuracoes configuracoes;
    public ConfiguracoesLogic configuracoesLogic;

    public long ultimaConsulta = 0;
    public long tempoEntreConsultas = 20000;
    
    @PostConstruct
    public void init() {
        configuracoesLogic = new ConfiguracoesLogicImpl();
    }

    
    public Configuracoes getConfiguracoes() {
        if(ultimaConsulta < new Date().getTime()){
            ultimaConsulta = new Date().getTime() + tempoEntreConsultas;
            configuracoes = configuracoesLogic.recupararConfiguracoes();
        }
        return configuracoes;
    }

    public void setConfiguracoes(Configuracoes configuracoes) {
        this.configuracoes = configuracoes;
    }


}
