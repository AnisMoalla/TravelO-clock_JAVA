/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oussema.Entities;

import java.sql.Time;

/**
 *
 * @author masso
 */
public class EncapsulationVendeur {
    private static int id;
    private static int user_id;
    private static String pays;
    private static String ville;
    private static String image;
    private static int tel;
    private static String nom;
    private static String  type;
    private static Time heure_ouverture;
    private static Time heure_fermeture;

    public EncapsulationVendeur() {
    }

    public EncapsulationVendeur(int id, int user_id, String pays, String ville, String image, int tel, String nom, String type, Time heure_ouverture, Time heure_fermeture) {
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

    public EncapsulationVendeur(int user_id, String pays, String ville, String image, int tel, String nom, String type, Time heure_ouverture, Time heure_fermeture) {
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

    public EncapsulationVendeur(int id) {
        this.id = id;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        EncapsulationVendeur.id = id;
    }

    public static int getUser_id() {
        return user_id;
    }

    public static void setUser_id(int user_id) {
        EncapsulationVendeur.user_id = user_id;
    }

    public static String getPays() {
        return pays;
    }

    public static void setPays(String pays) {
        EncapsulationVendeur.pays = pays;
    }

    public static String getVille() {
        return ville;
    }

    public static void setVille(String ville) {
        EncapsulationVendeur.ville = ville;
    }

    public static String getImage() {
        return image;
    }

    public static void setImage(String image) {
        EncapsulationVendeur.image = image;
    }

    public static int getTel() {
        return tel;
    }

    public static void setTel(int tel) {
        EncapsulationVendeur.tel = tel;
    }

    public static String getNom() {
        return nom;
    }

    public static void setNom(String nom) {
        EncapsulationVendeur.nom = nom;
    }

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        EncapsulationVendeur.type = type;
    }

    public static Time getHeure_ouverture() {
        return heure_ouverture;
    }

    public static void setHeure_ouverture(Time heure_ouverture) {
        EncapsulationVendeur.heure_ouverture = heure_ouverture;
    }

    public static Time getHeure_fermeture() {
        return heure_fermeture;
    }

    public static void setHeure_fermeture(Time heure_fermeture) {
        EncapsulationVendeur.heure_fermeture = heure_fermeture;
    }

    @Override
    public String toString() {
        return "Vendeur{" + "id=" + id + ", user_id=" + user_id + ", pays=" + pays + ", ville=" + ville + ", image=" + image + ", tel=" + tel + ", nom=" + nom + ", type=" + type + ", heure_ouverture=" + heure_ouverture + ", heure_fermeture=" + heure_fermeture + '}';
    }
   
    
}
