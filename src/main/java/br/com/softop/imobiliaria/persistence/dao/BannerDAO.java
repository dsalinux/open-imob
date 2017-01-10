package br.com.softop.imobiliaria.persistence.dao;

import br.com.softop.imobiliaria.entity.Banner;
import java.util.List;

/**
 *
 * @author danilo
 */
public interface BannerDAO extends GenericDAO<Banner, Integer> {
    
    List<Banner> buscarAtivos();
    
}
