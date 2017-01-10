package br.com.softop.imobiliaria.persistence.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Danilo Souza Almeida
 */
public interface GenericDAO<T, I extends Serializable> extends Serializable{
    
    T save(T entity);
    T saveNotCommit(T entity);
    void delete(T entity);
    void refresh(T entity);
    void initializer(List<T> entitys);
    T findById(I id);
    T findById(I id, boolean closeSession);
    void clear();
    List<T> findByExample(T entity, String...filds);
    List<T> findAll();
}
