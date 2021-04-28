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
public class Comment {
    private int id ;
    private String content;
    private int idPost;

    public Comment() {
    }

    public Comment(int id, String content, int idPost) {
        this.id = id;
        this.content = content;
        this.idPost = idPost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", content=" + content + ", idPost=" + idPost + '}';
    }
    
    
   
    
    
}
