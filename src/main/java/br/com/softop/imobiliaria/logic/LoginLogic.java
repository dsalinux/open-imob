package br.com.softop.imobiliaria.logic;

import br.com.softop.imobiliaria.entity.Login;
import br.com.softop.imobiliaria.util.exception.BusinessException;

/**
 *
 * @author danilo
 */
public interface LoginLogic extends GenericLogic<Login, Integer> {
 
    Login logar(String email, String senha) throws BusinessException;
    
}
