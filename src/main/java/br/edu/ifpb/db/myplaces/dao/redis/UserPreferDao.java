package br.edu.ifpb.db.myplaces.dao.redis;

import br.edu.ifpb.db.myplaces.entitys.User;

/**
 *
 * @author João Marcos F <joaomarccos.ads@gmail.com>
 */
public class UserLoginDao {
    
    private final RedisConnectionUtil rcu;

    public UserLoginDao() {        
        this.rcu = new RedisConnectionUtil();
    }
    
    public void save(User user, String password){
        rcu.getConnection().set(user.getEmail(), password);
        rcu.closeConnection();
    }
    
    public void updatePassword(User user, String newPassword){
        save(user, newPassword);
    }
    
    public String getPassword(String email){
        return rcu.getConnection().get(email);
    }
    
    public void remove(User user){
        rcu.getConnection().del(user.getEmail());
    }
}
