package br.edu.ifpb.db.myplaces.entitys;

/**
 *
 * @author João Marcos F <joaomarccos.ads@gmail.com>
 */
public class Place {
    
    private int id;
    private String description;
    private double lat;
    private double lng;

    public Place() {
    }

    public Place(String description, double lat, double lng) {
        this.description = description;
        this.lat = lat;
        this.lng = lng;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
    
    
}