package br.com.softop.imobiliaria.view;

import br.com.softop.imobiliaria.entity.Inquilino;
import br.com.softop.imobiliaria.logic.impl.InquilinoLogicImpl;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author danilo
 */
//@Controller
@ManagedBean
@SessionScoped
public class InquilinoView extends GenericBean<Inquilino, InquilinoLogicImpl> {
    
    public void atenderCliente(Inquilino inquilino){
        setEntity(inquilino);
        if(getEntity().getDataAtendimento() == null){
            getEntity().setDataAtendimento(new Date());
        } else {
            getEntity().setDataAtendimento(null);
        }
        save();
    }
    
}
