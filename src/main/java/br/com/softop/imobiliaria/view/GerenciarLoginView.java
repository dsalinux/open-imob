package br.com.softop.imobiliaria.view;

import br.com.softop.imobiliaria.entity.Login;
import br.com.softop.imobiliaria.logic.impl.LoginLogicImpl;
import br.com.softop.imobiliaria.util.StringHelper;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

@ManagedBean
@ViewScoped
public class GerenciarLoginView extends GenericBean<Login, LoginLogicImpl> {
 
        private String senha;

    @Override
    public void save(ActionEvent actionEvent) {
        if(!StringHelper.isEmpty(senha)){
            getEntity().setSenha(senha);
        }
        super.save(actionEvent);
    }
    
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
