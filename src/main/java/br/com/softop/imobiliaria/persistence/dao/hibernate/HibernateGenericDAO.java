/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.softop.imobiliaria.persistence.dao.hibernate;

import br.com.softop.imobiliaria.persistence.dao.GenericDAO;
import br.com.softop.imobiliaria.util.HibernateUtil;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

/**
 *
 * @author danilo
 */
public class HibernateGenericDAO<T, I extends Serializable> implements GenericDAO<T, I> {

    private Session session;
    private Class<T> persistentClass;
    
    @Override
    public T save(T entity) {
        Transaction transaction;
        if(getSession().getTransaction() == null){
            transaction = getSession().beginTransaction();
        } else {
            transaction = getSession().getTransaction();
            if(!transaction.isActive()){
                transaction.begin();
            }
        }
        entity = (T)getSession().merge(entity);
        transaction.commit();
        getSession().flush();
        getSession().close();
        return entity;
    }
    
    @Override
    public T saveNotCommit(T entity) {
        Transaction transaction;
        if(getSession().getTransaction() == null){
            transaction = getSession().beginTransaction();
        } else {
            transaction = getSession().getTransaction();
            if(!transaction.isActive()){
                transaction.begin();
            }
        }
        try{
            entity = (T)getSession().merge(entity);
        } catch (Exception e){
        }
        return entity;
    }

    @Override
    public void delete(T entity) {
        Transaction transaction = getSession().beginTransaction();
        getSession().delete(entity);
        transaction.commit();
        getSession().close();
    }

    @Override
    public void refresh(T entity){
        getSession().refresh(entity);
    }
    
    @Override
    public void initializer(List<T> entitys){
        for (T entity : entitys) {
            Hibernate.initialize(entity);
        }
    }
    
    @Override
    public T findById(I id) {
        T entity = (T) getSession().get(getPersistentClass(), id);
        return entity;
    }

    @Override
    public List<T> findByExample(T entity, String... filds) {
        getSession().flush();
        getSession().clear();
        Criteria crit = getSession().createCriteria(getPersistentClass());
        Example example =  Example.create(entity);
        for (String exclude : filds) {
            example.excludeProperty(exclude);
        }
        crit.add(example);
        return crit.list();
    }

    @Override
    public List<T> findAll(){
//        getSession().flush();Quando o cliente clica em editar depois apaga um campo e tenta pesquisar novametne, da erro! por isso foi comentado
        getSession().clear();
        Criteria crit = getSession().createCriteria(getPersistentClass());
        return crit.list();
    }
    
    @Override
    public void clear(){
        getSession().clear();
    }
    
    public Session getSession(){
        if(this.session == null){
            this.session = HibernateUtil.getSessionFactory().openSession();
        }else if(!this.session.isOpen()){
            this.session = HibernateUtil.getSessionFactory().openSession();
        }
        return this.session;
    }
    public void setSession(Session session){
        this.session = session;
    }
    
    public Class<T> getPersistentClass(){
        persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return persistentClass;
    }
    
}
