package br.edu.ifpb.db.myplaces.dao.redis;

import br.edu.ifpb.db.myplaces.entitys.Prefer;
import br.edu.ifpb.db.myplaces.entitys.User;
import com.google.gson.Gson;

/**
 *
 * @author João Marcos F <joaomarccos.ads@gmail.com>
 */
public class UserPreferDao {
    
    private final RedisConnectionUtil rcu;

    public UserPreferDao() {        
        this.rcu = new RedisConnectionUtil();
    }
    
    public void savePrefer(User user, Prefer prefer){
        rcu.getConnection().set(user.getEmail(), new Gson().toJson(prefer));
        rcu.closeConnection();
    }
    
    public void updatePrefer(User user, Prefer prefer){
        savePrefer(user, prefer);
    }
    
    public Prefer getPrefer(String email){
        return new Gson().fromJson(rcu.getConnection().get(email), Prefer.class);
    }
    
    public void remove(User user){
        rcu.getConnection().del(user.getEmail());
    }
}
