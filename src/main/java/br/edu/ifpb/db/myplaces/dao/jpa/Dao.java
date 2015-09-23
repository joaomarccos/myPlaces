package br.edu.ifpb.db.myplaces.dao.jpa;

/**
 *
 * @author João Marcos F <joaomarccos.ads@gmail.com>
 */
public interface Dao<T> {

    public boolean save(T object);

    public boolean update(T object);

    public boolean remove(T object);

    public T find(Object key, Class<T> classe);
}
