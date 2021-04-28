/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelpidev.entities;

/**
 *
 * @author Wofurani
 */
public class PostForum {

    private int id;
    private String Title;
    private String contenu;
    private int catId;
    

    public PostForum() {
    }

    public PostForum( String Title, String contenu) {
        this.Title = Title;
        this.contenu = contenu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    @Override
    public String toString() {
        return "PostForum{" + "id=" + id + ", Title=" + Title + ", contenu=" + contenu + '}';
    }
     
  
    
}
