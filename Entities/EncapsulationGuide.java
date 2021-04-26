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
public class EncapsulationGuide {
    private static int id;
    private static int user_id;
    private static String pays;
    private static String ville;
    private static String image;
    private static int tel;
    private static int age;
    private static String sexe;
    private static String nom;
    private static String prenom;
    private static String language;
    private static int vote;
    private static double rate;

   public EncapsulationGuide(int id, int user_id, String pays, String ville, String image, int tel, int age, String sexe, String nom, String prenom, String language, int vote, double rate) {
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

    public EncapsulationGuide() {
    }

    public EncapsulationGuide(int user_id, String pays, String ville, String image, int tel, int age, String sexe, String nom, String prenom, String language, int vote, double rate) {
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

    public EncapsulationGuide(int id) {
        this.id = id;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        EncapsulationGuide.id = id;
    }

    public static int getUser_id() {
        return user_id;
    }

    public static void setUser_id(int user_id) {
        EncapsulationGuide.user_id = user_id;
    }

    public static String getPays() {
        return pays;
    }

    public static void setPays(String pays) {
        EncapsulationGuide.pays = pays;
    }

    public static String getVille() {
        return ville;
    }

    public static  void setVille(String ville) {
        EncapsulationGuide.ville = ville;
    }

    public static String getImage() {
        return image;
    }

    public static void setImage(String image) {
        EncapsulationGuide.image = image;
    }

    public static int getTel() {
        return tel;
    }

    public static void setTel(int tel) {
        EncapsulationGuide.tel = tel;
    }

    public static int getAge() {
        return age;
    }

    public static void setAge(int age) {
        EncapsulationGuide.age = age;
    }

    public static String getSexe() {
        return sexe;
    }

    public static void setSexe(String sexe) {
        EncapsulationGuide.sexe = sexe;
    }

    public static String getNom() {
        return nom;
    }

    public static void setNom(String nom) {
        EncapsulationGuide.nom = nom;
    }

    public static String getPrenom() {
        return prenom;
    }

    public static void setPrenom(String prenom) {
        EncapsulationGuide.prenom = prenom;
    }

    public static String getLanguage() {
        return language;
    }

    public static void setLanguage(String language) {
        EncapsulationGuide.language = language;
    }

    public static int getVote() {
        return vote;
    }

    public static void setVote(int vote) {
        EncapsulationGuide.vote = vote;
    }

    public static double getRate() {
        return rate;
    }

    public static void setRate(double rate) {
        EncapsulationGuide.rate = rate;
    }
    
     @Override
    public String toString() {
        return "Guide{" + "user_id= " + user_id + "pays= " + pays + "ville= " + ville + "image= " + image + "tel= " + tel + "age= " + age + "sexe= " + sexe + "nom= " + nom + "prenom= " + prenom + "language= " + language + "vote= " + vote + "rate= " + rate + "}";
    }
    
    
    
}
