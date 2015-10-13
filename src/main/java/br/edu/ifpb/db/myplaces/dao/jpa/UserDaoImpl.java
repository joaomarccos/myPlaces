package br.edu.ifpb.db.myplaces.dao.jpa;

import br.edu.ifpb.db.myplaces.entitys.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author joaomarcos
 */
public class UserDaoImpl extends GenericJpaDao<User> implements UserDao {

    public UserDaoImpl(String persistenceUnity) {
        super(persistenceUnity);
    }

    @Override
    public List<User> findByName(String name) {
        if (name == null || name.isEmpty()) {
            return new ArrayList<>();
        }

        TypedQuery<User> jpql = this.entityManager.createQuery("SELECT u FROM User u where u.name like :name", User.class);
        name = name.concat("%");
        return jpql.setParameter("name", name).getResultList();
    }

}
