/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oussema.Entities;

/**
 *
 * @author masso
 */
public class Guide {
    private int id;
    private int user_id;
    private String pays;
    private String ville;
    private String image;
    private int tel;
    private int age;
    private String sexe;
    private String nom;
    private String prenom;
    private String language;
    private int vote;
    private double rate;

    public Guide(int id, int user_id, String pays, String ville, String image, int tel, int age, String sexe, String nom, String prenom, String language, int vote, double rate) {
        this.id = id;
        this.user_id = user_id;
        this.pays = pays;
        this.ville = ville;
        this.image = image;
        this.tel = tel;
        this.age = age;
        this.sexe = sexe;
        this.nom = nom;
        this.prenom = prenom;
        this.language = language;
        this.vote = vote;
        this.rate = rate;
    }

    public Guide() {
    }

    public Guide(int user_id, String pays, String ville, String image, int tel, int age, String sexe, String nom, String prenom, String language, int vote, double rate) {
        this.user_id = user_id;
        this.pays = pays;
        this.ville = ville;
        this.image = image;
        this.tel = tel;
        this.age = age;
        this.sexe = sexe;
        this.nom = nom;
        this.prenom = prenom;
        this.language = language;
        this.vote = vote;
        this.rate = rate;
    }

    public Guide(int id) {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
    
     @Override
    public String toString() {
        return "Guide{" + "user_id= " + user_id + "pays= " + pays + "ville= " + ville + "image= " + image + "tel= " + tel + "age= " + age + "sexe= " + sexe + "nom= " + nom + "prenom= " + prenom + "language= " + language + "vote= " + vote + "rate= " + rate + "}";
    }
    
}
