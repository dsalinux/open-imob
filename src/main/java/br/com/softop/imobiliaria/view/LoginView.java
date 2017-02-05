/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.softop.imobiliaria.view;

import br.com.softop.imobiliaria.entity.Login;
import br.com.softop.imobiliaria.logic.LoginLogic;
import br.com.softop.imobiliaria.logic.impl.LoginLogicImpl;
import br.com.softop.imobiliaria.util.Context;
import br.com.softop.imobiliaria.util.StringHelper;
import br.com.softop.imobiliaria.util.exception.BusinessException;
import com.ocpsoft.pretty.PrettyContext;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author danilo
 */
//@Controller
@ManagedBean
@SessionScoped
public class LoginView extends JSFUtil {

    private String email;
    private String senha;
    private String repetirSenha;
    private String url;
    private LoginLogic loginLogic = new LoginLogicImpl();
    private Login usuarioLogado;

    public void logar() {
        try {
            usuarioLogado = loginLogic.logar(email, senha);
            senha = null;
            Context.setLogin(usuarioLogado);
        } catch (BusinessException ex) {
            addMessage(FacesMessage.SEVERITY_WARN, ex.getMessage());
            Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void redirecionar() {
        try {
            if(StringHelper.isEmpty(url)){
                url = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("retorno");
                if (StringHelper.isEmpty(url)) {
                    url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/admin";
                }
            }
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } catch (IOException ex) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Erro ao redirecionar a página!");
            Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void redirecionarPermissao() {
        url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/admin/sem-permissao";
        redirecionar();
    }

    public void redirecionarLogin() {
        try {
            this.url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PrettyContext.getCurrentInstance().getRequestURL().toURL();
            String urlRedirect = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/admin/login";
            FacesContext.getCurrentInstance().getExternalContext().redirect(urlRedirect);
        } catch (IOException ex) {
            try {
                addMessage(FacesMessage.SEVERITY_ERROR, "Erro ao redirecionar!");
                FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/home");
                Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex1) {
                Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }

    }

    public void alterarSenha() {
        try {
            if (StringHelper.isEmpty(senha) || StringHelper.isEmpty(repetirSenha)) {
                addMessage(FacesMessage.SEVERITY_WARN, "Informe e repita a nova senha nos campos!");
                return;
            }
            if (!senha.equals(repetirSenha)) {
                addMessage(FacesMessage.SEVERITY_WARN, "As senhas digitadas nos dois campos estão diferentes! Digite a mesma senha em ambas as caixas!");
            }
            Login login = Context.getLogin();
            login.setSenha(senha);
            login.setAlterarSenha(false);
            login = loginLogic.save(login);
            Context.setLogin(login);
        } catch (BusinessException ex) {
            addMessage(FacesMessage.SEVERITY_WARN, ex.getMessage());
            Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isPronto() {
        if (Context.getLogin() != null && !Context.getLogin().getAlterarSenha()) {
            return true;
        }
        return false;
    }

    public boolean isLoggado(){
        return usuarioLogado != null;
    }

    public Login getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Login usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getRepetirSenha() {
        return repetirSenha;
    }

    public void setRepetirSenha(String repetirSenha) {
        this.repetirSenha = repetirSenha;
    }
}
