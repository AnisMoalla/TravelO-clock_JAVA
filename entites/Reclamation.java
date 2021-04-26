package entites;

import java.util.Date;

public class Reclamation {

    private String description,type,etat ;
    private Date date_reclamation;
    private int id,user_id,evenement_id;

    public Reclamation(String description, String type, String etat, Date date_reclamation) {
        this.description = description;
        this.type = type;
        this.etat = etat;
        this.date_reclamation = date_reclamation;
    }

    @Override
    public String toString() {
        return "Reclamation{" +
                "description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", etat='" + etat + '\'' +
                ", date_reclamation=" + date_reclamation +
                ", id=" + id +
                ", user_id=" + user_id +
                ", evenement_id=" + evenement_id +
                '}';
    }

    public Reclamation(String description, String type, String etat, Date date_reclamation, int id, int user_id, int evenement_id) {
        this.description = description;
        this.type = type;
        this.etat = etat;
        this.date_reclamation = date_reclamation;
        this.id = id;
        this.user_id = user_id;
        this.evenement_id = evenement_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public Reclamation() {
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getDate_reclamation() {
        return date_reclamation;
    }

    public void setDate_reclamation(Date date_reclamation) {
        this.date_reclamation = date_reclamation;
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

    public int getEvenement_id() {
        return evenement_id;
    }

    public void setEvenement_id(int evenement_id) {
        this.evenement_id = evenement_id;
    }
}
