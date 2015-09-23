package br.edu.ifpb.db.myplaces.entitys;

import java.util.Date;

/**
 *
 * @author João Marcos F <joaomarccos.ads@gmail.com>
 */
public class Comment {
    private int id;
    private Date date;
    private String description;
    private String author;

    public Comment() {
    }

    public Comment(int id, Date date, String description) {
        this.id = id;
        this.date = date;
        this.description = description;
    }
    
    
}
