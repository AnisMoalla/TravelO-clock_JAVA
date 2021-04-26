/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oussema.Entities;

import java.sql.Time;
import java.util.logging.Logger;

/**
 *
 * @author masso
 */
public class Vendeur {
    private int id;
    private int user_id;
    private String pays;
    private String ville;
    private String image;
    private int tel;
    private String nom;
    private String  type;
    private Time heure_ouverture;
    private Time heure_fermeture;

    public Vendeur() {
    }

    public Vendeur(int id, int user_id, String pays, String ville, String image, int tel, String nom, String type, Time heure_ouverture, Time heure_fermeture) {
        this.id = id;
        this.user_id = user_id;
        this.pays = pays;
        this.ville = ville;
        this.image = image;
        this.tel = tel;
        this.nom = nom;
        this.type = type;
        this.heure_ouverture = heure_ouverture;
        this.heure_fermeture = heure_fermeture;
    }

    public Vendeur(int user_id, String pays, String ville, String image, int tel, String nom, String type, Time heure_ouverture, Time heure_fermeture) {
        this.user_id = user_id;
        this.pays = pays;
        this.ville = ville;
        this.image = image;
        this.tel = tel;
        this.nom = nom;
        this.type = type;
        this.heure_ouverture = heure_ouverture;
        this.heure_fermeture = heure_fermeture;
    }

    public Vendeur(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Time getHeure_ouverture() {
        return heure_ouverture;
    }

    public void setHeure_ouverture(Time heure_ouverture) {
        this.heure_ouverture = heure_ouverture;
    }

    public Time getHeure_fermeture() {
        return heure_fermeture;
    }

    public void setHeure_fermeture(Time heure_fermeture) {
        this.heure_fermeture = heure_fermeture;
    }

    @Override
    public String toString() {
        return "Vendeur{" + "id=" + id + ", user_id=" + user_id + ", pays=" + pays + ", ville=" + ville + ", image=" + image + ", tel=" + tel + ", nom=" + nom + ", type=" + type + ", heure_ouverture=" + heure_ouverture + ", heure_fermeture=" + heure_fermeture + '}';
    }
   
    
    
    
}
