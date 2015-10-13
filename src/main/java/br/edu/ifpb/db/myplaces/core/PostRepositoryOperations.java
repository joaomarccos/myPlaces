package br.edu.ifpb.db.myplaces.core;

import br.edu.ifpb.db.myplaces.dao.DaoFactory;
import br.edu.ifpb.db.myplaces.dao.jpa.Dao;
import br.edu.ifpb.db.myplaces.dao.mongodb.PostDao;
import br.edu.ifpb.db.myplaces.dao.neo4j.UserLikeDao;
import br.edu.ifpb.db.myplaces.dao.neo4j.UserPlaceDao;
import br.edu.ifpb.db.myplaces.entitys.Comment;
import br.edu.ifpb.db.myplaces.entitys.Place;
import br.edu.ifpb.db.myplaces.entitys.Post;
import br.edu.ifpb.db.myplaces.entitys.User;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;

/**
 * manage posts, comments, likes..
 *
 * @author joaomarcos
 */
public class PostRepositoryOperations {

    private final Dao<User> userDao = DaoFactory.createGenericDaoJpa();
    private final PostDao postDao = DaoFactory.createPostDao();
    private final UserLikeDao likesDao = DaoFactory.createUserLikeDao();
    private final UserPlaceDao placeDao = DaoFactory.createUserPlaceDao();

    public void newPost(String author, String userName, String text, String placeDescription, double lat, double lng) {
        Post post = new Post();
        post.setAuthor(author);
        post.setText(text);
        post.setUsername(userName);
        post.setDate(Calendar.getInstance().getTime());
        Place place = new Place(placeDescription, lat, lng);
        post.setlocation(place);
        postDao.save(post);
        placeDao.registerNewPlace(place, author, LocalDateTime.ofInstant(post.getDate().toInstant(), ZoneId.systemDefault()));
    }

    public void removePost(String postId) {
        postDao.remove(postId);
    }

    public List<Post> listAllPosts(String email) {
        return postDao.find(email);
    }

    public void updatePost(Post post) {
        postDao.update(post);
    }

    public void commentPost(String postId, String commentText, Date date, String author, String username) {
        Comment comment = new Comment(author, date, commentText, username);
        postDao.comment(postId, comment);
    }

    public void editComment(String postID, String commentID, String newComment) {
        postDao.updateComment(postID, commentID, newComment);
    }

    public void deleteComment(String postID, String commentId) {
        postDao.removeComment(postID, commentId);
    }

    public void likePost(String email, String postID) {
        likesDao.likePost(email, postID);
    }

    public void dislikePost(String email, String postID) {
        likesDao.dislikePost(email, postID);
    }

    public boolean isLikedFor(String postID, String email) {
        return likesDao.isLikedFor(postID, email);
    }

    public int numberOfLikes(String postId) {
        return likesDao.numberOfLikes(postId);
    }

    public List<User> findUsersThatLikeIt(String postID) {
        List<String> emails = likesDao.findUsersThatLikeIt(postID);
        User user;
        List<User> users = new ArrayList<>();
        for (String email : emails) {
            if ((user = userDao.find(email, User.class)) != null) {
                users.add(user);
            }
        }
        return users;
    }
}
