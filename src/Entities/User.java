package Entities;

import java.util.Date;

public class User {

    int id ;
    String nom ;
    String prenom ;
    String email ;
    String password ;
    int age ;
    double cin ;
    String photo ;
    double numero ;
    String etat ;
    String roles ;
    String country ;
    Date created_at ;
    String resetToken ;
    String activationtoken ;
    String verified ;

    public User() {}

    public User(int id, String nom, String prenom, String email, String password, int age, double cin, String photo, double numero, String etat, String roles, String country, Date created_at, String resetToken, String activationtoken, String verified) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.age = age;
        this.cin = cin;
        this.photo = photo;
        this.numero = numero;
        this.etat = etat;
        this.roles = roles;
        this.country = country;
        this.created_at = created_at;
        this.resetToken = resetToken;
        this.activationtoken = activationtoken;
        this.verified = verified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getCin() {
        return cin;
    }

    public void setCin(double cin) {
        this.cin = cin;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public double getNumero() {
        return numero;
    }

    public void setNumero(double numero) {
        this.numero = numero;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public String getActivationtoken() {
        return activationtoken;
    }

    public void setActivationtoken(String activationtoken) {
        this.activationtoken = activationtoken;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", cin=" + cin +
                ", photo='" + photo + '\'' +
                ", numero=" + numero +
                ", etat='" + etat + '\'' +
                ", roles='" + roles + '\'' +
                ", country='" + country + '\'' +
                ", created_at=" + created_at +
                ", resetToken='" + resetToken + '\'' +
                ", activationtoken='" + activationtoken + '\'' +
                ", verified='" + verified + '\'' +
                '}';
    }
}
