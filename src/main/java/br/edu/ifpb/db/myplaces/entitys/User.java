package br.edu.ifpb.db.myplaces.entitys;

import java.io.Serializable;
import java.util.Base64;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author João Marcos F <joaomarccos.ads@gmail.com>
 */
@Entity
@Table(name = "Users")
public class User implements Serializable {

    @Id
    private String email;
    private String name;
    private int age;
    private String bio;
    @Embedded
    private Address address;
    @Lob
    private byte[] image;
    private String password;

    public User() {
    }

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Address getAdress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImg() {
        if (image != null) {
            return miniature();
        }
        return "img/default_user.png";
    }

    private String miniature() {
        StringBuilder sb = new StringBuilder();
        sb.append("data:image/png;base64,");
        sb.append(Base64.getEncoder().encodeToString(image));
        return sb.toString();
    }

}
