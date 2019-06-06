/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.softop.imobiliaria.logic;

import br.com.softop.imobiliaria.entity.Sobre;
import br.com.softop.imobiliaria.util.exception.BusinessException;
import java.io.Serializable;

/**
 *
 * @author danilo
 */
public interface SobreLogic extends Serializable{
    
    Sobre salvar(Sobre sobre) throws BusinessException;
    Sobre recupararSobre();
    
}
