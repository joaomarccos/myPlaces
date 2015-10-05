package br.edu.ifpb.db.myplaces.core;

import br.edu.ifpb.db.myplaces.core.exceptions.CreateAccountException;
import br.edu.ifpb.db.myplaces.core.exceptions.LoginException;
import br.edu.ifpb.db.myplaces.dao.DaoFactory;
import br.edu.ifpb.db.myplaces.dao.jpa.Dao;
import br.edu.ifpb.db.myplaces.dao.neo4j.RelationshipDao;
import br.edu.ifpb.db.myplaces.dao.neo4j.UserPlaceDao;
import br.edu.ifpb.db.myplaces.dao.redis.UserPreferDao;
import br.edu.ifpb.db.myplaces.entitys.Place;
import br.edu.ifpb.db.myplaces.entitys.Prefer;
import br.edu.ifpb.db.myplaces.entitys.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joaomarcos
 */
public class UsersRepositoryOperations {

    private final Dao<User> dao = DaoFactory.createDaoJpa();
    private final UserPreferDao preferDao = DaoFactory.createUserLoginDao();
    private final RelationshipDao relationshipDao = DaoFactory.createRelationshipDao();
    private final UserPlaceDao placeDao = DaoFactory.createUserPlaceDao();

    public User registerNewUser(String userName, String email, String password) throws CreateAccountException {
        User user = new User();
        user.setName(userName);
        user.setEmail(email);
        user.setPassword(Encrypter.encrypt(password));
        if (dao.save(user)) {
            preferDao.savePrefer(user, new Prefer("Arial", "default"));
            return dao.find(email, User.class);
        }

        throw new CreateAccountException("Não foi possivel Criar a conta. Esse email pode estar em uso");
    }

    public User login(String email, String password) throws LoginException {
        User user = dao.find(email, User.class);
        if (user == null) {
            throw new LoginException("Usuário não registrado");
        }
        if (Encrypter.encrypt(password).equals(user.getPassword())) {
            return user;
        } else {
            throw new LoginException("Email ou senha inválidos");
        }
    }

    public void updatePassword(User user, String newPassword) {
        user.setPassword(newPassword);
        dao.update(user);
    }

    public void updateInfo(User user) {
        dao.update(user);
    }

    public void deleteAccount(User user) {
        preferDao.remove(user);
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

    public List<User> following(String email) {
        List<String> userEmails = relationshipDao.following(email);
        List<User> following = new ArrayList<>();
        User user;
        for (String e : userEmails) {
            if ((user = dao.find(e, User.class)) != null) {
                following.add(user);
            }
        }
        return following;
    }

    public List<Place> suggestedPlacesFor(String email) {
        return placeDao.suggestedPlaces(email);
    }

    public User getUser(String email) {
        return dao.find(email, User.class);
    }

    public void changeUserTheme(User user, String font, String colorTheme) {
        preferDao.updatePrefer(user, new Prefer(font, colorTheme));
    }

    public Prefer getTheme(User user) {
        return preferDao.getPrefer(user.getEmail());
    }
}
