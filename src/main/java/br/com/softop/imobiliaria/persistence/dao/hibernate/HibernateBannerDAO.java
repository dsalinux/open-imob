package br.com.softop.imobiliaria.persistence.dao.hibernate;

import br.com.softop.imobiliaria.entity.Banner;
import br.com.softop.imobiliaria.persistence.dao.BannerDAO;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author danilo
 */
//@Service
public class HibernateBannerDAO extends HibernateGenericDAO<Banner, Integer> implements BannerDAO {

    @Override
    public List<Banner> buscarAtivos() {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.isNull("dataDesativacao"));
        List<Banner> banners = criteria.list();
        getSession().close();
        return banners;
    }
    
}
