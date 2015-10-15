package br.edu.ifpb.db.myplaces.core;

import br.edu.ifpb.db.myplaces.entitys.Post;
import br.edu.ifpb.db.myplaces.entitys.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author joaomarcos
 */
public class Feed {

    private final UsersRepositoryOperations uro = new UsersRepositoryOperations();
    private final PostRepositoryOperations pro = new PostRepositoryOperations();

    class PostWithPriority implements Comparable<PostWithPriority> {

        String requestUser;
        Post post;
        int priority;

        public PostWithPriority(String requestUser, Post post, int numberLikes) {
            this.requestUser = requestUser;
            this.post = post;
            setPriority(numberLikes, post, requestUser);
        }

        private void setPriority(int numberLikes, Post post1, String requestUser1) {
            this.priority = (pro.isLikedFor(post1.getId(), requestUser1)) ? numberLikes : numberLikes * 30;
        }

        @Override
        public int compareTo(PostWithPriority o) {
            LocalDate thisDate = LocalDateTime.ofInstant(post.getDate().toInstant(), ZoneId.systemDefault()).toLocalDate();
            LocalDate oDate = LocalDateTime.ofInstant(o.post.getDate().toInstant(), ZoneId.systemDefault()).toLocalDate();
            int compare = oDate.compareTo(thisDate);
            if (compare == 0) {
                return priority - o.priority;
            }
            return compare;
        }
    }

    public List<Post> update(User request) {
        Queue<PostWithPriority> allPosts = new PriorityQueue<>();
        List<Post> posts = new ArrayList<>();
        List<User> users = uro.following(request.getEmail());
        users.add(request);
        for (User user : users) {
            List<Post> userPosts = pro.listAllPosts(user.getEmail());
            for (Post userPost : userPosts) {
                int nLikes = pro.numberOfLikes(userPost.getId());
                PostWithPriority postWithPriority = new PostWithPriority(request.getEmail(), userPost, nLikes);
                allPosts.add(postWithPriority);
            }
        }
        for (PostWithPriority post : allPosts) {
            posts.add(post.post);
        }
        return posts;
    }
}
