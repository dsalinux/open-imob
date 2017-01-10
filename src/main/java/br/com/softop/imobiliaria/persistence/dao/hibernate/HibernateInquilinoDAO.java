package br.com.softop.imobiliaria.persistence.dao.hibernate;

import br.com.softop.imobiliaria.entity.Inquilino;
import br.com.softop.imobiliaria.persistence.dao.InquilinoDAO;
import org.springframework.stereotype.Service;

/**
 *
 * @author danilo
 */
@Service
public class HibernateInquilinoDAO extends HibernateGenericDAO<Inquilino, Integer> implements InquilinoDAO{
    
}
