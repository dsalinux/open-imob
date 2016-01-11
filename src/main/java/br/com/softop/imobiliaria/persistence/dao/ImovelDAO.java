package br.com.softop.imobiliaria.persistence.dao;

import br.com.softop.imobiliaria.entity.Imovel;
import br.com.softop.imobiliaria.entity.vo.ImovelVO;
import java.util.List;

/**
 *
 * @author danilo
 */
public interface ImovelDAO extends GenericDAO<Imovel, Integer> {
 
    List<String> buscarCidades(String nome);
    List<String> buscarBairros(String cidade, String bairro);
    List<Imovel> buscarAtivosPorFiltro(ImovelVO imovelVO);
}
