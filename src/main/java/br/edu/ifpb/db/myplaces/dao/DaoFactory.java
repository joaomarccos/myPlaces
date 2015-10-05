package br.edu.ifpb.db.myplaces.dao;

import br.edu.ifpb.db.myplaces.dao.jpa.Dao;
import br.edu.ifpb.db.myplaces.dao.jpa.GenericJpaDao;
import br.edu.ifpb.db.myplaces.dao.mongodb.PostDao;
import br.edu.ifpb.db.myplaces.dao.neo4j.RelationshipDao;
import br.edu.ifpb.db.myplaces.dao.neo4j.UserLikeDao;
import br.edu.ifpb.db.myplaces.dao.neo4j.UserPlaceDao;
import br.edu.ifpb.db.myplaces.dao.redis.UserPreferDao;

/**
 *
 * @author João Marcos F <joaomarccos.ads@gmail.com>
 */
public class DaoFactory {

    public static Dao createDaoJpa() {
        return new GenericJpaDao("br.edu.ifpb.db_mls");
    }
    
    public static UserPreferDao createUserLoginDao(){
        return new UserPreferDao();
    }
    
    public static PostDao createPostDao(){
        return new PostDao();
    }
    
    public static RelationshipDao createRelationshipDao(){
        return new RelationshipDao();
    }
    
    public static UserLikeDao createUserLikeDao(){
        return new UserLikeDao();
    }
    
    public static UserPlaceDao createUserPlaceDao(){
        return new UserPlaceDao();
    }
        
}
