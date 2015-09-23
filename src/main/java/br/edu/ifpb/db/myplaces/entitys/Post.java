package br.edu.ifpb.db.myplaces.entitys;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author João Marcos F <joaomarccos.ads@gmail.com>
 */
public class Post {

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
}
