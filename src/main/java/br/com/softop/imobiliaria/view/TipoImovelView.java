package br.com.softop.imobiliaria.view;

import br.com.softop.imobiliaria.entity.TipoImovel;
import br.com.softop.imobiliaria.logic.impl.TipoImovelLogicImpl;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.springframework.stereotype.Controller;

/**
 *
 * @author danilo
 */
@Controller
@ManagedBean
@ViewScoped
public class TipoImovelView extends GenericBean<TipoImovel, TipoImovelLogicImpl> {
    
}
