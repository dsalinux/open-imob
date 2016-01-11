package br.com.softop.imobiliaria.logic.impl;

import br.com.softop.imobiliaria.entity.Login;
import br.com.softop.imobiliaria.logic.LoginLogic;
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
public class LoginLogicImpl implements LoginLogic {

    @Override
    public Login save(Login entity) throws BusinessException {
        if(StringHelper.isEmpty(entity.getLogin())){
            throw new BusinessException("Por favor informe o login!");
        }
        if(StringHelper.isEmpty(entity.getEmail()) || !Assert.isValidEmail(entity.getEmail())){
            throw new BusinessException("Por favor informe o email corretamente");
        }
        if(entity.getId() == null){
            Login l = ContextDAO.getLoginDAO().buscarPorEmail(entity.getEmail());
            if(l != null){
                throw new BusinessException("Email já está cadastrado para o login: " + l.getLogin() + ". Por favor informe outro email.");
            }
        }
        if(StringHelper.isEmpty(entity.getSenha())){
            throw new BusinessException("Por fovar informe a senha!");
        }
        if(entity.getDataCadastro() == null){
            entity.setDataCadastro(new Date());
        }
        
        return ContextDAO.getLoginDAO().save(entity);
            
    }

    @Override
    public void delete(Login entity) {
        entity = findById(entity.getId());
        delete(entity);
    }

    @Override
    public Login findById(Integer id) {
        return ContextDAO.getLoginDAO().findById(id);
    }

    @Override
    public List<Login> find(Login entity) throws BusinessException {
        return ContextDAO.getLoginDAO().findAll();
    }
    
    @Override
    public Login logar(String email, String senha) throws BusinessException {
        if(StringHelper.isEmpty(email) || StringHelper.isEmpty(senha)){
            throw new BusinessException("Por favor informe o E-mail e a Senha!");
        }
        Login login = ContextDAO.getLoginDAO().buscarPorEmail(email);
        if(login == null || !login.getSenha().equals(senha)){
            throw new BusinessException("E-mail ou Senha invalidos!");
        }
        return login;
    }
    
}
