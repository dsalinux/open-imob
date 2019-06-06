package br.com.softop.imobiliaria.persistence.dao.hibernate;

import br.com.softop.imobiliaria.entity.Cliente;
import br.com.softop.imobiliaria.persistence.dao.ClienteDAO;
import br.com.softop.imobiliaria.util.StringHelper;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author danilo
 */
public class HibernateClienteDAO extends HibernateGenericDAO<Cliente, Integer> implements ClienteDAO {
 
    @Override
    public List<Cliente> buscar(Cliente entity){
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        if(!StringHelper.isEmpty(entity.getCpfCnpj())){
            criteria.add(Restrictions.eq("cpfCnpj", entity.getCpfCnpj()));
        }
        if(!StringHelper.isEmpty(entity.getNome())){
            criteria.add(Restrictions.ilike("nome", entity.getNome(), MatchMode.ANYWHERE));
        }
        List<Cliente> clientes = criteria.list();
        getSession().close();
        return clientes;
    }
    
}
