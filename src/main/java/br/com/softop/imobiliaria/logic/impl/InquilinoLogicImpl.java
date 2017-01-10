package br.com.softop.imobiliaria.logic.impl;

import br.com.softop.imobiliaria.entity.Inquilino;
import br.com.softop.imobiliaria.logic.InquilinoLogic;
import br.com.softop.imobiliaria.util.ContextDAO;
import br.com.softop.imobiliaria.util.StringHelper;
import br.com.softop.imobiliaria.util.exception.BusinessException;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author danilo
 */
@Service
public class InquilinoLogicImpl implements InquilinoLogic {

    @Override
    public Inquilino save(Inquilino entity) throws BusinessException {
        if (StringHelper.isEmpty(entity.getNome())) {
            throw new BusinessException("Por favor informe seu nome!");
        }
        if (StringHelper.isEmpty(entity.getEmail()) && StringHelper.isEmpty(entity.getTelefone())) {
            throw new BusinessException("Por favor informe o email ou telefone para contato!");
        }
        if(StringHelper.isEmpty(entity.getDescricao())){
            throw new BusinessException("Por favor informe a descrição do imóvel que você quer!");
        }
        if(entity.getDataCadastro() == null){
            entity.setDataCadastro(new Date());
        }
        return ContextDAO.getInquilinoDAO().save(entity);
    }

    @Override
    public void delete(Inquilino entity) throws BusinessException {
        entity = findById(entity.getId());
        ContextDAO.getInquilinoDAO().delete(entity);
    }

    @Override
    public Inquilino findById(Integer id) {
        return ContextDAO.getInquilinoDAO().findById(id);
    }

    @Override
    public List<Inquilino> find(Inquilino entity) throws BusinessException {
        return ContextDAO.getInquilinoDAO().findAll();
    }

    @Override
    public Inquilino findById(Inquilino entity) {
        return findById(entity.getId());
    }
    
}
