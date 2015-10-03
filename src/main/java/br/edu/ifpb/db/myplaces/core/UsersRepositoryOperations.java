package br.edu.ifpb.db.myplaces.core;

import br.edu.ifpb.db.myplaces.core.exceptions.CreateAccountException;
import br.edu.ifpb.db.myplaces.core.exceptions.LoginException;
import br.edu.ifpb.db.myplaces.dao.DaoFactory;
import br.edu.ifpb.db.myplaces.dao.jpa.Dao;
import br.edu.ifpb.db.myplaces.dao.neo4j.RelationshipDao;
import br.edu.ifpb.db.myplaces.dao.redis.UserLoginDao;
import br.edu.ifpb.db.myplaces.entitys.User;
import com.sun.corba.se.spi.activation.RepositoryOperations;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joaomarcos
 */
public class UsersRepositoryOperations {

    private final Dao<User> dao = DaoFactory.createDaoJpa();
    private final UserLoginDao loginDao = DaoFactory.createUserLoginDao();
    private final RelationshipDao relationshipDao = DaoFactory.createRelationshipDao();

    public User registerNewUser(String userName, String email, String password) throws CreateAccountException {
        User user = new User();
        user.setName(userName);
        user.setEmail(email);

        if (dao.save(user)) {
            loginDao.save(user, Encrypter.encrypt(password));
            return dao.find(email, User.class);
        }

        throw new CreateAccountException("Não foi possivel Criar a conta. Esse email pode estar em uso");
    }

    public User login(String email, String password) throws LoginException {
        String storedPassword = loginDao.getPassword(email);
        if (storedPassword == null || storedPassword.isEmpty()) {
            throw new LoginException("Usuário não registrado");
        }
        if (Encrypter.encrypt(password).equals(storedPassword)) {
            return dao.find(email, User.class);
        } else {
            throw new LoginException("Email ou senha inválidos");
        }
    }

    public void updatePassword(User user, String newPassword) {
        loginDao.updatePassword(user, newPassword);
    }

    public void updateInfo(User user) {
        dao.update(user);
    }

    public void deleteAccount(User user) {
        loginDao.remove(user);
        dao.remove(user);
    }

    public void followAnUser(String userEmail, String userEmailToFollow) {
        relationshipDao.follow(userEmail, userEmailToFollow);
    }

    public void unfollowAnUser(String userEmail, String userEmailToUnfollow) {
        relationshipDao.unfollow(userEmail, userEmailToUnfollow);
    }

    public boolean isFollower(String userEmail, String userEmailToCheck) {
        return relationshipDao.isFollower(userEmail, userEmailToCheck);
    }

    public int numberOfFollowers(String email) {
        return relationshipDao.numberOfFollwers(email);
    }

    public List<User> suggestedPersonsToFollow(String userEmail) {
        List<String> userEmails = relationshipDao.suggestPersonsFor(userEmail);
        List<User> suggestedUsers = new ArrayList<>();
        User user;
        for (String email : userEmails) {
            if ((user = dao.find(email, User.class)) != null) {
                suggestedUsers.add(user);
            }
        }
        return suggestedUsers;
    }
}
