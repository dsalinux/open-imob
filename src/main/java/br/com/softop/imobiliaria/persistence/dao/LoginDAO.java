package br.com.softop.imobiliaria.persistence.dao;

import br.com.softop.imobiliaria.entity.Login;

/**
 *
 * @author danilo
 */
public interface LoginDAO extends GenericDAO<Login, Integer> {
    Login buscarPorEmail(String email);
    
}
