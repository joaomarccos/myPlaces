package br.edu.ifpb.db.myplaces.entitys;

import java.util.Date;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author João Marcos F <joaomarccos.ads@gmail.com>
 */
public class Comment {

    private String id;
    private Date date;
    private String description;
    private String author;

    public Comment() {
    }

    public Comment(String author, Date date, String description) {
        this.author = author;
        this.date = date;
        this.description = description;
        this.id = ObjectId.get().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String _id) {
        this.id = _id;
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Document toDocument() {
        Document doc = new Document();
        doc.append("id", this.id).append("author", this.author).append("date", this.date).append("description", this.description);
        return doc;
    }

    public static Comment fromDocument(Document document) {                
        Comment comment = new Comment();
        comment.setId(document.getString("id"));
        comment.setAuthor(document.getString("author"));
        comment.setDate(document.getDate("date"));
        comment.setDescription(document.getString("description"));        

        return comment;
    }

}
