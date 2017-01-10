package br.com.softop.imobiliaria.persistence.dao.hibernate;

import br.com.softop.imobiliaria.entity.Orientacoes;
import br.com.softop.imobiliaria.persistence.dao.OrientacoesDAO;
import org.springframework.stereotype.Service;

/**
 *
 * @author danilo
 */
@Service
public class HibernateOrientacoesDAO extends HibernateGenericDAO<Orientacoes, Integer> implements OrientacoesDAO {
    
}
