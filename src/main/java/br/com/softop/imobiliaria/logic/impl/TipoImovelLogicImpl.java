package br.com.softop.imobiliaria.logic.impl;

import br.com.softop.imobiliaria.entity.TipoImovel;
import br.com.softop.imobiliaria.logic.TipoImovelLogic;
import br.com.softop.imobiliaria.util.ContextDAO;
import br.com.softop.imobiliaria.util.StringHelper;
import br.com.softop.imobiliaria.util.exception.BusinessException;
import java.util.List;

/**
 *
 * @author danilo
 */
public class TipoImovelLogicImpl implements TipoImovelLogic {

    @Override
    public TipoImovel save(TipoImovel entity) throws BusinessException {
        if(StringHelper.isEmpty(entity.getNome())){
            throw new BusinessException("Por favor informe o nome!");
        }
        return ContextDAO.getTipoImovelDAO().save(entity);
    }

    @Override
    public void delete(TipoImovel entity) throws BusinessException {
        entity = findById(entity.getId());
        ContextDAO.getTipoImovelDAO().delete(entity);
    }

    @Override
    public TipoImovel findById(Integer id) {
        return ContextDAO.getTipoImovelDAO().findById(id);
    }

    @Override
    public List<TipoImovel> find(TipoImovel entity) throws BusinessException {
        return ContextDAO.getTipoImovelDAO().findAll();
    }

    @Override
    public TipoImovel findById(TipoImovel entity) {
        return findById(entity.getId());
    }
    
}
