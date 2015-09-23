package br.edu.ifpb.db.myplaces.entitys;

/**
 *
 * @author João Marcos F <joaomarccos.ads@gmail.com>
 */
public class User {

    private String email;
    private String name;
    private int age;
    private String bio;
    private Address address;
    private String image;

    public User() {
    }

    public User(String email, String name, int age, String bio) {
        this.email = email;
        this.name = name;
        this.age = age;
        this.bio = bio;
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

    private Address getAdress() {
        return this.address;
    }

    private void setAddress(Address address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }    
}
