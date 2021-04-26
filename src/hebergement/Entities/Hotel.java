/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author LENOVO
 */
public class Hotel {
    
    
    private int id ,nbrechambre,nbrechambreDispo,nbreEtoile;
    private float  prix ;
    private String nom , description,adresse,image;

    public Hotel() {
    }
public Hotel(int id, int nbrechambre, int nbrechambreDispo, int nbreEtoile, float prix, String nom, String description, String adresse, String image) {
        this.id = id;
        this.nbrechambre = nbrechambre;
        this.nbrechambreDispo = nbrechambreDispo;
        this.nbreEtoile = nbreEtoile;
        this.prix = prix;
        this.nom = nom;
        this.description = description;
        this.adresse = adresse;
        this.image = image;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbrechambre() {
        return nbrechambre;
    }

    public void setNbrechambre(int nbrechambre) {
        this.nbrechambre = nbrechambre;
    }

    public int getNbrechambreDispo() {
        return nbrechambreDispo;
    }

    public void setNbrechambreDispo(int nbrechambreDispo) {
        this.nbrechambreDispo = nbrechambreDispo;
    }

    public int getNbreEtoile() {
        return nbreEtoile;
    }

    public void setNbreEtoile(int nbreEtoile) {
        this.nbreEtoile = nbreEtoile;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Hotel{" + "id=" + id + ", nbrechambre=" + nbrechambre + ", nbrechambreDispo=" + nbrechambreDispo + ", nbreEtoile=" + nbreEtoile + ", prix=" + prix + ", nom=" + nom + ", description=" + description + ", adresse=" + adresse + ", image=" + image + '}';
    }


}
