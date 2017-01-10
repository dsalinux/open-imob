package br.com.softop.imobiliaria.persistence.dao.hibernate;

import br.com.softop.imobiliaria.entity.Login;
import br.com.softop.imobiliaria.persistence.dao.LoginDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

/**
 *
 * @author danilo
 */
@Service
public class HibernateLoginDAO extends HibernateGenericDAO<Login, Integer> implements LoginDAO {

    @Override
    public Login buscarPorEmail(String email) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("email", email));
        Login login = (Login) criteria.uniqueResult();
        return login;
    }
}
