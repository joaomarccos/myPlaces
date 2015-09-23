package br.edu.ifpb.db.myplaces.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author João Marcos F <joaomarccos.ads@gmail.com>
 */
public class GenericJpaDao<T> implements Dao<T> {

    private EntityManager entityManager;

    public GenericJpaDao() {
        this("dev");
    }

    public GenericJpaDao(String persistenceUnity) {
        this.entityManager = Persistence.createEntityManagerFactory(persistenceUnity).createEntityManager();
    }

    @Override
    public boolean save(T object) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(object);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        }
    }

    @Override
    public boolean update(T object) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(object);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        }
    }

    @Override
    public boolean remove(T object) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(object);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        }
    }

    @Override
    public T find(Object key, Class<T> classe) {
        return entityManager.find(classe, key);
    }

}
