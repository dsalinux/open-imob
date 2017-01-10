package br.com.softop.imobiliaria.persistence.dao;

import br.com.softop.imobiliaria.entity.Cliente;
import java.util.List;

/**
 *
 * @author danilo
 */
public interface ClienteDAO extends GenericDAO<Cliente, Integer> {
    
    List<Cliente> buscar(Cliente entity);
    
}
