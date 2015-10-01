package br.edu.ifpb.db.myplaces.dao.redis;

import br.edu.ifpb.db.myplaces.entitys.User;

/**
 *
 * @author João Marcos F <joaomarccos.ads@gmail.com>
 */
public class UserDaoRedis {
    
    private final RedisConnectionUtil rcu;

    public UserDaoRedis() {        
        this.rcu = new RedisConnectionUtil();
    }
    
    public void save(User user, String password){
        rcu.getConnection().set(user.getEmail(), password);
        rcu.closeConnection();
    }
    
    public void updatePassword(User user, String newPassword){
        save(user, newPassword);
    }
    
    public String getPassword(User user){
        return rcu.getConnection().get(user.getEmail());
    }
    
    public void remove(User user){
        rcu.getConnection().del(user.getEmail());
    }
}
