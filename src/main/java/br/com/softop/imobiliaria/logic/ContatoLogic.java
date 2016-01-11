package br.com.softop.imobiliaria.logic;

import br.com.softop.imobiliaria.entity.Configuracoes;
import br.com.softop.imobiliaria.entity.Imovel;
import br.com.softop.imobiliaria.entity.vo.ContatoVO;
import br.com.softop.imobiliaria.util.exception.BusinessException;

/**
 *
 * @author danilo
 */
public interface ContatoLogic {

    void enviarMensagem(ContatoVO contatoVO) throws BusinessException;
    void solicitarImovel(Imovel imovel, ContatoVO contatoVO) throws BusinessException;
    void testarEnvioDeEmail(Configuracoes configuracoes);
    
}
