package br.com.softop.imobiliaria.logic.impl;

import br.com.softop.imobiliaria.entity.Cliente;
import br.com.softop.imobiliaria.logic.ClienteLogic;
import br.com.softop.imobiliaria.util.Assert;
import br.com.softop.imobiliaria.util.ContextDAO;
import br.com.softop.imobiliaria.util.StringHelper;
import br.com.softop.imobiliaria.util.exception.BusinessException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author danilo
 */
public class ClienteLogicImpl implements ClienteLogic {

    @Override
    public Cliente save(Cliente entity) throws BusinessException {
        validarDados(entity);
        return ContextDAO.getClienteDAO().save(entity);

    }

    private void validarDados(Cliente entity) throws BusinessException {
        if (entity.getTipoCliente() == null) {
            throw new BusinessException("Erro ao verificar o Tipo de Cliente!");
        }
        if (Cliente.TipoCliente.JURIDICA.equals(entity.getTipoCliente())) {
            if (StringHelper.isEmpty(entity.getCpfCnpj())) {
                throw new BusinessException("Informe o CNPJ!");
            } else if (!Assert.isCnpjValido(entity.getCpfCnpj())) {
                throw new BusinessException("CNPJ inválido! Confira os números digitados.");
            }
        } else {
            if (StringHelper.isEmpty(entity.getCpfCnpj())) {
                throw new BusinessException("Informe o CPF!");
            } else if (!Assert.isCpf(entity.getCpfCnpj())) {
                throw new BusinessException("CPF inválido! Confira os números digitados.");
            }
        }

        if (StringHelper.isEmpty(entity.getNome())) {
            throw new BusinessException("Informe o nome!");
        }
        if(entity.getDataCadastro() == null){
            entity.setDataCadastro(new Date());
        }
    }

    @Override
    public void delete(Cliente entity) throws BusinessException {
        entity = findById(entity.getId());
        try {
            ContextDAO.getClienteDAO().delete(entity);
        } catch (Exception e) {
            throw new BusinessException("Este cliente possui lançamentos em serviços e fluxo de caixa! Primeiro apague estes lançamentos!");
        }
    }

    @Override
    public Cliente findById(Integer id) {
        return ContextDAO.getClienteDAO().findById(id);
    }

    @Override
    public List<Cliente> find(Cliente entity) throws BusinessException {
        return ContextDAO.getClienteDAO().buscar(entity);
    }
    
}
