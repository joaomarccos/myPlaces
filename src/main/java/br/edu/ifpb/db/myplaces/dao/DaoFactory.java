package br.edu.ifpb.db.myplaces.dao;

import br.edu.ifpb.db.myplaces.dao.jpa.Dao;
import br.edu.ifpb.db.myplaces.dao.jpa.GenericJpaDao;

/**
 *
 * @author João Marcos F <joaomarccos.ads@gmail.com>
 */
public class DaoFactory {

    public static Dao createDaoJpa() {
        return new GenericJpaDao();
    }
}
