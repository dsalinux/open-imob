package br.com.softop.imobiliaria.logic.impl;

import br.com.softop.imobiliaria.entity.Configuracoes;
import br.com.softop.imobiliaria.logic.ConfiguracoesLogic;
import br.com.softop.imobiliaria.util.Assert;
import br.com.softop.imobiliaria.util.ContextDAO;
import br.com.softop.imobiliaria.util.StringHelper;
import br.com.softop.imobiliaria.util.exception.BusinessException;

/**
 *
 * @author danilo
 */
public class ConfiguracoesLogicImpl implements ConfiguracoesLogic {

    @Override
    public Configuracoes salvar(Configuracoes configuracoes) throws BusinessException {
        if(StringHelper.isEmpty(configuracoes.getEmailsRecebimento())){
            throw new BusinessException("Informe os emails de recebimento separados por virgula!");
        }
        String[] emails = configuracoes.getEmailsRecebimento().split(",");
        for(int i = 0; i < emails.length; i++){
            if(!Assert.isValidEmail(emails[i])){
                throw new BusinessException("O email " + emails[i] + " está incorreto! Verifique a pontuação e o @! Ex.: email@email.com");
            }
        }
        if(StringHelper.isEmpty(configuracoes.getEmailEnvio()) || !Assert.isValidEmail(configuracoes.getEmailEnvio())){
            throw new BusinessException("Por favor infome o email de envio corretamente! Verifique a pontuação e o @! Ex.: email@email.com");
        }
        return ContextDAO.getConfiguracoesDAO().save(configuracoes);
    }

    @Override
    public Configuracoes recupararConfiguracoes() {
        /*TODO caso não exista as configurações, devemos criar um default, mas 
        para implantação na imperial esta melhoria ficará para depois*/
//        if(configuracoes.getLatitude() == null || configuracoes.getLatitude().compareTo(0D) <= 0){
//            if(configuracoes.getLongitude() == null || configuracoes.getLongitude().compareTo(0D) <= 0){
//                configuracoes.setLatitude(-17.7504272);
//                configuracoes.setLongitude(-46.1844659);
//            }
//        }
        return ContextDAO.getConfiguracoesDAO().findById(1);
    }
    
}
