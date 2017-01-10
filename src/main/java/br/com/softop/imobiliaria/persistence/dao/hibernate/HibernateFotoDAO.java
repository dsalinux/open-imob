package br.com.softop.imobiliaria.persistence.dao.hibernate;

import br.com.softop.imobiliaria.entity.Foto;
import br.com.softop.imobiliaria.persistence.dao.FotoDAO;
import org.springframework.stereotype.Service;

/**
 *
 * @author danilo
 */
@Service
public class HibernateFotoDAO extends HibernateGenericDAO<Foto, Integer> implements FotoDAO {
    
}
