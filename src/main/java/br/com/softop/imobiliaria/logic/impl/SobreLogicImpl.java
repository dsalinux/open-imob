package br.com.softop.imobiliaria.logic.impl;

import br.com.softop.imobiliaria.entity.Sobre;
import br.com.softop.imobiliaria.logic.SobreLogic;
import br.com.softop.imobiliaria.util.ContextDAO;
import br.com.softop.imobiliaria.util.StringHelper;
import br.com.softop.imobiliaria.util.exception.BusinessException;

/**
 *
 * @author danilo
 */
public class SobreLogicImpl implements SobreLogic {

    @Override
    public Sobre salvar(Sobre sobre) throws BusinessException {
        if(StringHelper.isEmpty(sobre.getTitulo())){
            throw new BusinessException("Por favor informe o titulo!");
        }
        if(StringHelper.isEmpty(sobre.getTexto())){
            throw new BusinessException("Por favor informe o texto!");
        }
        if(sobre.getTexto().length() > 65000){
            throw new BusinessException("Texto muito grande! Por favor informe um texto menor!");
        }
        return ContextDAO.getSobreDAO().save(sobre);
    }

    @Override
    public Sobre recupararSobre() {
        Sobre sobre = ContextDAO.getSobreDAO().findById(1);
        if(sobre == null){
            sobre = new Sobre();
            sobre.setId(1);
            sobre.setTitulo("Titulo!");
            sobre.setTexto("Texto Exemplo!");
        }
        return sobre;
    }

    
    
}
