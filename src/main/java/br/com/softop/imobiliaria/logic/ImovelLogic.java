package br.com.softop.imobiliaria.logic;

import br.com.softop.imobiliaria.entity.Imovel;
import br.com.softop.imobiliaria.entity.vo.ImovelVO;
import java.util.List;

/**
 *
 * @author danilo
 */
public interface ImovelLogic extends GenericLogic<Imovel, Integer> {
    
        List<String> listarCidades();
        List<String> listarCidades(String cidade);
        List<String> buscarBairros(String cidade);
        List<String> buscarBairros(String cidade, String bairro);
        List<Imovel> buscarAtivosPorFiltro(ImovelVO imovelVO);
        List<Imovel> buscarAtivosDestaque();
}
