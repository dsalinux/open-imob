package br.com.softop.imobiliaria.view;

import br.com.softop.imobiliaria.logic.GenericLogic;
import br.com.softop.imobiliaria.util.exception.BusinessException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Danilo Souza Almeida
 */
public abstract class GenericBean<E, L extends GenericLogic> extends JSFUtil {
    
    private static final long serialVersionUID = 1L;
    
    public static enum CurrentState {
        EDIT,
        INSERT,
        SEARCH
    }
    
    private Class<L> classLogic;
    private Class<E> classEntity;
    private E entity;
    private List<E> entitys;
    private GenericLogic genericLogic;
    
    private CurrentState currentState;

    public GenericBean  (){
        try {
            entity = getClassEntity().newInstance();
            entitys = new ArrayList<E>();
            genericLogic = getClassLogic().newInstance();
            setCurrentState(CurrentState.SEARCH);
        } catch (InstantiationException ex) {
            Logger.getLogger(GenericBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GenericBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void newRegistre(){
        try {
            entity = getClassEntity().newInstance();
            setCurrentState(CurrentState.INSERT);
        } catch (InstantiationException ex) {
            Logger.getLogger(GenericBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GenericBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void save(){
        try {
            entity = save(entity);
            entity = getClassEntity().newInstance();
            setCurrentState(CurrentState.SEARCH);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", "Salvo com sucesso!"));
        } catch (BusinessException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", ex.getMessage()));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Desculpe, mas parece que ocorreu um erro na aplicação!\n"+ex.getMessage()));
            Logger.getLogger(GenericBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public E save(E entity) throws Exception{
        return (E) getGenericLogic().save(entity);
    }
    
    public void delete(){
        try {
            getGenericLogic().delete(entity);
            getEntitys().remove(getEntity());
            newRegistre();
            search();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Removido com sucesso!!"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Desculpe, mas parece que ocorreu um erro na aplicação!"));
            Logger.getLogger(GenericBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void edit(E entity){
        this.entity = (E) genericLogic.findById(entity);
        setCurrentState(CurrentState.EDIT);
    }
    
    public void search(){
        try {
            if(currentState.equals(CurrentState.SEARCH)){
                entitys = genericLogic.find(entity);
                if(entitys == null || entitys.isEmpty()){
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Nenhum dado encontrado!"));
                }
            } else {
                    entity = getClassEntity().newInstance();
                    setCurrentState(CurrentState.SEARCH);
            }
        } catch (BusinessException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", ex.getMessage()));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Desculpe, mas parece que ocorreu um erro na aplicação!"));
            Logger.getLogger(GenericBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Getters and setters
    public String getCurrentStateName() {
        return currentState.name();
    }
    public CurrentState getCurrentState() {
        return currentState;
    }
    public void setCurrentState(CurrentState currentState) {
        this.currentState = currentState;
    }

    public E getEntity() {
        return entity;
    }
    public void setEntity(E entity) {
        this.entity = entity;
    }

    public List<E> getEntitys() {
        return entitys;
    }
    public void setEntitys(List<E> entitys) {
        this.entitys = entitys;
    }

    public GenericLogic getGenericLogic() throws Exception{
        if(genericLogic == null){
            genericLogic = getClassLogic().newInstance();
        }
        return genericLogic;
    }
    
    public L getLogic(){
        try {
            return (L)getGenericLogic();
        } catch (Exception ex) {
            Logger.getLogger(GenericBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void setGenericLogic(GenericLogic genericLogic) {
        this.genericLogic = genericLogic;
    }

    public Class<E> getClassEntity() {
        classEntity = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return classEntity;
    }
    public Class<L> getClassLogic() {
        classLogic = (Class<L>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        return classLogic;
    }
    
    
    
}
