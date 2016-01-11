package br.com.softop.imobiliaria.logic.impl;

import br.com.softop.imobiliaria.entity.Orientacoes;
import br.com.softop.imobiliaria.logic.OrientacoesLogic;
import br.com.softop.imobiliaria.util.ContextDAO;
import br.com.softop.imobiliaria.util.StringHelper;
import br.com.softop.imobiliaria.util.exception.BusinessException;

/**
 *
 * @author danilo
 */
public class OrientacoesLogicImpl implements OrientacoesLogic {

    @Override
    public Orientacoes salvar(Orientacoes orientacoes) throws BusinessException {
        if(StringHelper.isEmpty(orientacoes.getTexto())){
            throw new BusinessException("Por favor informe o texto!");
        }
        if(orientacoes.getTexto().length() > 15000){
            throw new BusinessException("Texto muito grande! Por favor informe um texto menor!");
        }
        return ContextDAO.getOrientacoesDAO().save(orientacoes);
    }

    @Override
    public Orientacoes recupararOrientacoes() {
        Orientacoes orientacoes = ContextDAO.getOrientacoesDAO().findById(1);
        if(orientacoes == null){
            orientacoes = new Orientacoes();
            orientacoes.setId(1);
            orientacoes.setTexto("Texto Exemplo!");
        }
        return orientacoes;
    }

    
    
}
