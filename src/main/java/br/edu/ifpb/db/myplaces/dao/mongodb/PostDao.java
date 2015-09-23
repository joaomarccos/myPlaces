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

    private MongoDBConnectionUtil mongoUtil;
    private MongoCollection<Document> collection;

    public PostDao() {
        this.mongoUtil = new MongoDBConnectionUtil();
    }
    
    public void save(Post post) {
        collection = mongoUtil.getConnection().getCollection("Post");
        collection.insertOne(post.toDocument());
        mongoUtil.closeConnection();
    }

    public void remove(Post post) {
        collection = mongoUtil.getConnection().getCollection("Post");
        collection.deleteOne(eq("_id", post.getId()));
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
        collection.updateOne(eq("_id",post.getId()), new Document("$set", post.toDocument()));
        mongoUtil.closeConnection();
    }

    public void comment(ObjectId postID, Comment comment) {
        collection = mongoUtil.getConnection().getCollection("Post");
        collection.updateOne(eq("_id",postID), new Document("$addToSet", new Document("comments", comment.toDocument())));
        mongoUtil.closeConnection();
    }

    public void updateComment(Comment comment) {
        
    }

    public void removeComment(String commentId) {
        
    }
}
