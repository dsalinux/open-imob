/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.softop.imobiliaria.logic;

import br.com.softop.imobiliaria.entity.Configuracoes;
import br.com.softop.imobiliaria.util.exception.BusinessException;

/**
 *
 * @author danilo
 */
public interface ConfiguracoesLogic {
    
    Configuracoes salvar(Configuracoes configuracoes) throws BusinessException;
    Configuracoes recupararConfiguracoes();
    
}
