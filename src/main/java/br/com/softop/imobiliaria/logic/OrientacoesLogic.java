/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.softop.imobiliaria.logic;

import br.com.softop.imobiliaria.entity.Orientacoes;
import br.com.softop.imobiliaria.util.exception.BusinessException;
import java.io.Serializable;

/**
 *
 * @author danilo
 */
public interface OrientacoesLogic extends Serializable{
    
    Orientacoes salvar(Orientacoes orientacoes) throws BusinessException;
    Orientacoes recupararOrientacoes();
    
}
