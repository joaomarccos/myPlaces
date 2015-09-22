package br.edu.ifpb.db.myplaces.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @author job
 */
public class DAO<T>{
    
    private EntityManager entityManager;
    
    public DAO(){
        this("desenvolvimento");
    }        
    
    public DAO(String unidadePersistencia){
        entityManager=Persistence.createEntityManagerFactory(unidadePersistencia).createEntityManager();
        
    }
    
    public boolean salvar(T obj) {
        EntityTransaction transacao=entityManager.getTransaction();
        
        try{
           transacao.begin();
           entityManager.persist(obj);
           transacao.commit();   
           return true;
        }catch(Exception ex){
            if(transacao.isActive())
               transacao.rollback();
            return false;
        }
        
    }

    public boolean atualizar(T obj) {
        EntityTransaction transacao=entityManager.getTransaction();
        
        try{
           transacao.begin();
           entityManager.merge(obj);
           transacao.commit();   
           return true;
        }catch(Exception ex){
            if(transacao.isActive())
               transacao.rollback();
            return false;
        }
    }

    public boolean excluir(T obj) {
        EntityTransaction transacao=entityManager.getTransaction();
        
        try{
           transacao.begin();
           entityManager.remove(obj);
           transacao.commit();   
           return true;
        }catch(Exception ex){
            if(transacao.isActive())
               transacao.rollback();
            return false;
        }
    }

    public T buscar(Object chave, Class<T> entidade) {
        return entityManager.find(entidade, chave);
    }

}
