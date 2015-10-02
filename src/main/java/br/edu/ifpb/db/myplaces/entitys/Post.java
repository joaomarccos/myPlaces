package br.edu.ifpb.db.myplaces.entitys;

import com.mongodb.BasicDBList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author João Marcos F <joaomarccos.ads@gmail.com>
 */
public class Post {

    private ObjectId id;
    private String author;
    private Date date;
    private String text;
    private List<Comment> comments;
    private Place location;

    public Post() {
        this.comments = new ArrayList<>();
    }

    public Post(String author, Date date, String text) {
        this();
        this.author = author;
        this.date = date;
        this.text = text;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
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
        post.setId(document.getObjectId("_id"));
        post.setAuthor(document.getString("author"));
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
