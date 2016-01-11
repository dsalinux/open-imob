package br.com.softop.imobiliaria.view;

import br.com.softop.imobiliaria.entity.Configuracoes;
import br.com.softop.imobiliaria.logic.ConfiguracoesLogic;
import br.com.softop.imobiliaria.logic.impl.ConfiguracoesLogicImpl;
import br.com.softop.imobiliaria.logic.impl.ContatoLogicImpl;
import br.com.softop.imobiliaria.util.StringHelper;
import br.com.softop.imobiliaria.util.exception.BusinessException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author danilo
 */
@ManagedBean
@ApplicationScoped
public class ConfiguracoesView extends JSFUtil {
    
    public Configuracoes configuracoes;
    public ConfiguracoesLogic configuracoesLogic;
    public String senha;
    
    @PostConstruct
    public void init(){
        configuracoesLogic = new ConfiguracoesLogicImpl();
        configuracoes = configuracoesLogic.recupararConfiguracoes();
    }
    
    public void testarEnvioDeEmail(){
        try{
            new ContatoLogicImpl().testarEnvioDeEmail(configuracoes);
            addMessage(FacesMessage.SEVERITY_INFO, "Email teste enviado! Confira o recebimento do email de teste com os destinatários!\n Não esqueça de salvar as configurações!");
        } catch (Exception e){
            addMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu erro no envio: " + e.getMessage());
        }
    }

    public void salvar(){
        try {
            if(!StringHelper.isEmpty(senha)){
                configuracoes.setSenhaEnvio(senha);
            }
            configuracoes = configuracoesLogic.salvar(configuracoes);
            addMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!");
        } catch (BusinessException ex) {
            addMessage(FacesMessage.SEVERITY_WARN, ex.getMessage());
        } catch (Exception e){
            addMessage(FacesMessage.SEVERITY_ERROR, "Parece que nossos servidores estão com algum problema! Aguarde alguns instantes e tente novamente!");
            Logger.getLogger(ConfiguracoesView.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void atualizar(){
        configuracoes = configuracoesLogic.recupararConfiguracoes();
        if(configuracoes == null){
            addMessage(FacesMessage.SEVERITY_ERROR, "Erro ao buscar as configurções! Entre em contato com o administrador ou tente novamente em alguns instantes.");
        }
    }
    
    public Configuracoes getConfiguracoes() {
        return configuracoes;
    }
    public void setConfiguracoes(Configuracoes configuracoes) {
        this.configuracoes = configuracoes;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
