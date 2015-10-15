package br.edu.ifpb.db.myplaces.dao.mongodb;

import br.edu.ifpb.db.myplaces.entitys.Comment;
import br.edu.ifpb.db.myplaces.entitys.Post;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author João Marcos F <joaomarccos.ads@gmail.com>
 */
public class PostDao {

    private final MongoDBConnectionUtil mongoUtil;
    private MongoCollection<Document> collection;

    public PostDao() {
        this.mongoUtil = new MongoDBConnectionUtil();
    }
    
    public void save(Post post) {
        collection = mongoUtil.getConnection().getCollection("Post");
        collection.insertOne(post.toDocument());
        mongoUtil.closeConnection();
    }

    public void remove(String id) {
        collection = mongoUtil.getConnection().getCollection("Post");
        collection.deleteOne(eq("id", id));          
        mongoUtil.closeConnection();
    }

    public List<Post> find(String author) {
        collection = mongoUtil.getConnection().getCollection("Post");
        FindIterable result = collection.find(eq("author", author));
        
        final List<Post> posts = new ArrayList<>();
        result.forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {
                posts.add(Post.fromDocument(document));
            }
        });
        
        mongoUtil.closeConnection();
        return posts;
    }

    public void update(Post post) {
        collection = mongoUtil.getConnection().getCollection("Post");
        collection.updateOne(eq("id",post.getId()), new Document("$set", post.toDocument()));
        mongoUtil.closeConnection();
    }

    public void comment(String postID, Comment comment) {
        collection = mongoUtil.getConnection().getCollection("Post");
        collection.updateOne(eq("id",postID), new Document("$addToSet", new Document("comments", comment.toDocument())));
        mongoUtil.closeConnection();
    }

    public void updateComment(String postID, String commentID, String newComment) {
        collection = mongoUtil.getConnection().getCollection("Post");
        Document comment = new Document();
        comment.append("id", postID).append("comments.id", commentID);
        collection.updateOne(comment, new Document("$set",new Document("comments.$.description",newComment)));
        mongoUtil.closeConnection();
    }

    public void removeComment(String postID, String commentId) {
        collection = mongoUtil.getConnection().getCollection("Post");                        
        collection.updateOne(eq("id",postID), new Document("$pull", new Document("comments", new Document("id",commentId))));
        mongoUtil.closeConnection();
    }
}
