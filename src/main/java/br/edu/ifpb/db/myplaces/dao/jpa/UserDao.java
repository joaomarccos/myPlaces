package br.edu.ifpb.db.myplaces.dao.jpa;

import br.edu.ifpb.db.myplaces.entitys.User;
import java.util.List;

/**
 *
 * @author joaomarcos
 */
public interface UserDao extends Dao<User> {

    public List<User> findByName(String name);
}
