package br.edu.ifpb.db.myplaces.entitys;

import com.mongodb.BasicDBList;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author João Marcos F <joaomarccos.ads@gmail.com>
 */
public class Post implements Serializable{

    private String id;
    private String author;
    private String username;
    private Date date;
    private String text;
    private List<Comment> comments;
    private Place location;
            
    public Post() {
        this.comments = new ArrayList<Comment>();
        this.id = ObjectId.get().toString();
    }

    public Post(String author, Date date, String text) {
        this();
        this.author = author;
        this.date = date;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setLocation(Place location) {
        this.location = location;
    }
    
    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public Place getLocation() {
        return location;
    }

    public void setlocation(Place place) {
        this.location = place;
    }
    
    public Document toDocument() {
        Document doc = new Document();
        doc.append("author", this.author).append("date", this.date).append("text", this.text);
        doc.append("username", this.username).append("id", this.id);
        doc.append("location", this.location.toDocument());
        BasicDBList commentList = new BasicDBList();        
        for (Comment comment : comments) {
            commentList.add(comment.toDocument());
        }
        doc.append("comments", commentList);
        return doc;
    }

    public static Post fromDocument(Document document) {
        Post post = new Post();
        post.setId(document.getString("id"));
        post.setAuthor(document.getString("author"));
        post.setUsername(document.getString("username"));
        post.setText(document.getString("text"));
        post.setDate(document.getDate("date"));
        post.setlocation(Place.fromDocument((Document) document.get("location")));
        List<Document> docs = (List<Document>) document.get("comments");
        for (Document doc : docs) {
            post.addComment(Comment.fromDocument(doc));
        }
        return post;
    }

}
