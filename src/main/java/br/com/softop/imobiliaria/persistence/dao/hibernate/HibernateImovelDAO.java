package br.com.softop.imobiliaria.persistence.dao.hibernate;

import br.com.softop.imobiliaria.entity.Imovel;
import br.com.softop.imobiliaria.entity.vo.ImovelVO;
import br.com.softop.imobiliaria.persistence.dao.ImovelDAO;
import br.com.softop.imobiliaria.util.StringHelper;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author danilo
 */
public class HibernateImovelDAO extends HibernateGenericDAO<Imovel, Integer> implements ImovelDAO {

    @Override
    public List<String> buscarCidades(String nome) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        if (!StringHelper.isEmpty(nome)) {
            criteria.add(Restrictions.ilike("cidade", nome, MatchMode.START));
        }
        criteria.setProjection(Projections.distinct(Projections.property("cidade")));
        List list = criteria.list();
        getSession().close();
        return list;
    }

    @Override
    public List<Imovel> buscarAtivosPorFiltro(ImovelVO imovelVO) {

        Criteria criteria = getSession().createCriteria(getPersistentClass());

        criteria.add(Restrictions.isNull("dataDesativacao"));
        if(imovelVO.getTipo_() != null){
            criteria.add(Restrictions.eq("tipo_", imovelVO.getTipo_()));
        }
        if (imovelVO.getArmariosCozinha() != null) {
            criteria.add(Restrictions.eq("armariosEmbutidos", imovelVO.getArmariosCozinha()));
        }
        if (imovelVO.getGuardaRoupas() != null) {
            criteria.add(Restrictions.eq("guardaroupasEmbutidos", imovelVO.getGuardaRoupas()));
        }
        if (!StringHelper.isEmpty(imovelVO.getCidade())) {
            criteria.add(Restrictions.eq("cidade", imovelVO.getCidade()));
        }
        if (!StringHelper.isEmpty(imovelVO.getBairro())) {
            criteria.add(Restrictions.eq("bairro", imovelVO.getBairro()));
        }
        if (imovelVO.getValorMaximo() != null && imovelVO.getValorMaximo().floatValue() > 0) {
            criteria.add(Restrictions.and(Restrictions.gt("valor", 0F), Restrictions.le("valor", imovelVO.getValorMaximo().floatValue())));
        }
        if (imovelVO.getValorMinimo() != null && imovelVO.getValorMinimo().floatValue() > 0) {
            criteria.add(Restrictions.and(Restrictions.gt("valor", 0F), Restrictions.ge("valor", imovelVO.getValorMinimo().floatValue())));
        }
        
        if (imovelVO.getTipoImovel() != null) {
            criteria.createAlias("tipoImovel", "tipImov");
            criteria.add(Restrictions.eq("tipImov.id", imovelVO.getTipoImovel()));
        }
        if (imovelVO.getQuartos() != null) {
            criteria.add(Restrictions.eq("quartos", imovelVO.getQuartos()));
        }
        if (imovelVO.getSalas() != null) {
            criteria.add(Restrictions.eq("salas", imovelVO.getSalas()));
        }
        if (imovelVO.getBanheiros() != null) {
            criteria.add(Restrictions.eq("banheiros", imovelVO.getBanheiros()));
        }
        if (imovelVO.getGaragens() != null) {
            criteria.add(Restrictions.eq("garagem", imovelVO.getGaragens()));
        }

        criteria.addOrder(Order.asc("valor"));
        
        List<Imovel> l = criteria.list();
        for (Imovel imovel : l) {
            Hibernate.initialize(imovel.getFotoList());
        }
        getSession().close();
        return l;
    }

    @Override
    public Imovel findById(Integer id) {
        Imovel imovel = super.findById(id, false);
        Hibernate.initialize(imovel.getFotoList());
        getSession().close();
        return imovel;
    }
    

    @Override
    public List<String> buscarBairros(String cidade, String bairro) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        if (!StringHelper.isEmpty(bairro)) {
            criteria.add(Restrictions.ilike("bairro", bairro, MatchMode.START));
        }
        criteria.add(Restrictions.eq("cidade", cidade));
        criteria.setProjection(Projections.distinct(Projections.property("bairro")));
        List list = criteria.list();
        getSession().close();
        return list;
    }

    
    //criado para buscar todos os imoveis//
    @Override
    public List<Imovel> buscarTodos(boolean todos) {

        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.isNull("dataDesativacao"));
        List l = criteria.list();
        getSession().close();
        return l;
    }

    @Override
    public List<Imovel> buscarAtivosDestaquer() {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.isNull("dataDesativacao"));
        criteria.add(Restrictions.eq("imovelDestaque", true));
        criteria.addOrder(Order.desc("dataCadastro"));
        List<Imovel> imoveis = criteria.list();
        if(imoveis == null || imoveis.isEmpty()){
            Criteria criteria20Ultimos = getSession().createCriteria(getPersistentClass());
            criteria20Ultimos.add(Restrictions.isNull("dataDesativacao"));
            criteria20Ultimos.setMaxResults(12);
            imoveis = criteria20Ultimos.list();
        }
        for (Imovel imovel : imoveis) {
            Hibernate.initialize(imovel.getFotoList());
        }
        getSession().close();
        return imoveis;
    }

}
