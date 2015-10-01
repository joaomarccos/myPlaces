package br.edu.ifpb.db.myplaces.dao;

import br.edu.ifpb.db.myplaces.dao.jpa.Dao;
import br.edu.ifpb.db.myplaces.dao.jpa.GenericJpaDao;
import br.edu.ifpb.db.myplaces.dao.mongodb.PostDao;
import br.edu.ifpb.db.myplaces.dao.redis.UserDaoRedis;

/**
 *
 * @author João Marcos F <joaomarccos.ads@gmail.com>
 */
public class DaoFactory {

    public static Dao createDaoJpa() {
        return new GenericJpaDao();
    }
    
    public static UserDaoRedis createUserDaoToRedis(){
        return new UserDaoRedis();
    }
    
    public static PostDao createPostDao(){
        return new PostDao();
    }
}
