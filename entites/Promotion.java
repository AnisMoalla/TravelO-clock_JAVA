package entites;

import java.util.Date;

public class Promotion {

    private int id,hotel_id,user_id,pourcentage;
    private String nom;
    private Date date_debut,date_fin;

    public Promotion() {
    }

    public Promotion(int pourcentage, String nom, Date date_debut, Date date_fin) {
        this.pourcentage = pourcentage;
        this.nom = nom;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public Promotion(int id, int hotel_id, int user_id, int pourcentage, String nom, Date date_debut, Date date_fin) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.user_id = user_id;
        this.pourcentage = pourcentage;
        this.nom = nom;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(int pourcentage) {
        this.pourcentage = pourcentage;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "id=" + id +
                ", hotel_id=" + hotel_id +
                ", user_id=" + user_id +
                ", pourcentage=" + pourcentage +
                ", nom='" + nom + '\'' +
                ", date_debut=" + date_debut +
                ", date_fin=" + date_fin +
                '}';
    }
}
