package br.com.softop.imobiliaria.logic;

import br.com.softop.imobiliaria.entity.Banner;
import java.util.List;

/**
 *
 * @author danilo
 */
public interface BannerLogic extends GenericLogic<Banner, Integer> {
 
    List<Banner> buscarAtivos();
    
}
