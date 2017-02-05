package br.com.softop.imobiliaria.view;

import br.com.softop.imobiliaria.entity.Cliente;
import br.com.softop.imobiliaria.logic.impl.ClienteLogicImpl;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

//@Controller
@ManagedBean
@ViewScoped
public class ClienteView extends GenericBean<Cliente, ClienteLogicImpl> {
    
    private String tipoCliente = "FISICA";

    public void alterarTipoCliente(){
        if("FISICA".equals(tipoCliente)){
            getEntity().setTipoCliente(Cliente.TipoCliente.FISICA);
        } else {
            getEntity().setTipoCliente(Cliente.TipoCliente.JURIDICA);
        }
    }

    @Override
    public void newRegistre() {
        super.newRegistre();
        tipoCliente = getEntity().getTipoCliente().getName();
    }

    @Override
    public void edit(Cliente entity) {
        tipoCliente = entity.getTipoCliente().getName();
        super.edit(entity);
    }
    
    public String getTipoCliente() {
        return tipoCliente;
    }
    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }
    
}
