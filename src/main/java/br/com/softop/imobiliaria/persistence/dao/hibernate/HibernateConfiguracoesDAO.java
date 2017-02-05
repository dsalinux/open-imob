package br.com.softop.imobiliaria.persistence.dao.hibernate;

import br.com.softop.imobiliaria.entity.Configuracoes;
import br.com.softop.imobiliaria.persistence.dao.ConfiguracoesDAO;

/**
 *
 * @author danilo
 */
public class HibernateConfiguracoesDAO extends HibernateGenericDAO<Configuracoes, Integer> implements ConfiguracoesDAO {

    @Override
    public Configuracoes findById(Integer id) {
        getSession().flush();
        getSession().clear();
        return super.findById(id);
    }
    
    
    
}
