/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelpidev.entities;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Wofurani
 */
public class EventPlaning {
    private int id ;
    private String title;
    private int guideId;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    public EventPlaning() {
    }

    public EventPlaning(int id, String title, int guideId, LocalDate dateDebut, LocalDate dateFin) {
        this.id = id;
        this.title = title;
        this.guideId = guideId;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getGuideId() {
        return guideId;
    }

    public void setGuideId(int guideId) {
        this.guideId = guideId;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    @Override
    public String toString() {
        return "EventPlaning{" + "id=" + id + ", title=" + title + ", guideId=" + guideId + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + '}';
    }
    
    
    
    
    
    
    
}
