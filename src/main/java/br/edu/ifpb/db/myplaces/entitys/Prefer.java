package br.edu.ifpb.db.myplaces.entitys;

import java.io.Serializable;

/**
 *
 * @author joaomarcos
 */
public class Prefer implements Serializable{
    private String font;
    private String colorTheme;    

    public Prefer() {
    }        

    public Prefer(String font, String theme) {
        this.font = font;
        this.colorTheme = theme;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public String getColorTheme() {
        return colorTheme;
    }

    public void setColorTheme(String colorTheme) {
        this.colorTheme = colorTheme;
    }
    

}
