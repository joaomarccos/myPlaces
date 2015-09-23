package br.edu.ifpb.db.myplaces.entitys;

import com.mongodb.BasicDBList;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.bson.BsonArray;
import org.bson.BsonValue;
import org.bson.Document;

/**
 *
 * @author João Marcos F <joaomarccos.ads@gmail.com>
 */
public class Post {

    private String id;
    private String author;
    private Date date;
    private String text;
    private List<Comment> comments;

    public Post() {
        this.comments = new ArrayList<>();
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

    public Document toDocument() {
        Document doc = new Document();
        doc.append("author", this.author).append("date", this.date).append("text", this.text);
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
        post.setText(document.getString("text"));
        post.setDate(document.getDate("date"));
        List<Document> docs = (List<Document>) document.get("comments");
        for (Document doc : docs) {
            post.addComment(Comment.fromDocument(doc));
        }
        return post;
    }

}
