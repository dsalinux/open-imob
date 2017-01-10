package br.com.softop.imobiliaria.persistence.dao.hibernate;

import br.com.softop.imobiliaria.entity.TipoImovel;
import br.com.softop.imobiliaria.persistence.dao.TipoImovelDAO;
import org.springframework.stereotype.Service;

/**
 *
 * @author danilo
 */
@Service
public class HibernateTipoImovelDAO extends HibernateGenericDAO<TipoImovel, Integer> implements TipoImovelDAO {
    
}
