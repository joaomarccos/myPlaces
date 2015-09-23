package br.edu.ifpb.db.myplaces.entitys;

/**
 *
 * @author João Marcos F <joaomarccos.ads@gmail.com>
 */
public class Address {

    private String country;
    private String state;
    private String city;

    public Address() {
    }

    public Address(String country, String state, String city) {
        this.country = country;
        this.state = state;
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    
}
