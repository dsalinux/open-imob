package br.com.softop.imobiliaria.logic;

import br.com.softop.imobiliaria.util.exception.BusinessException;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author danilo
 */
public interface GenericLogic<T, I extends Serializable> extends Serializable{
 
    T save(T entity) throws BusinessException;
    void delete(T entity) throws BusinessException;
    T findById(I id);
    T findById(T entity);
    List<T> find(T entity) throws BusinessException;
    
}
