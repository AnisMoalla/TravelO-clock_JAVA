/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oussema.Entities;

import java.sql.Date;





/**
 *
 * @author masso
 */
public class ReservationGuide {
    private int id;
    private int id_guide;
    private int id_touriste;
    private Date date_reservation;

    public ReservationGuide() {
    }

    public ReservationGuide(int id, int id_guide, int id_touriste, Date date_reservation) {
        this.id = id;
        this.id_guide = id_guide;
        this.id_touriste = id_touriste;
        this.date_reservation = date_reservation;
    }

    public ReservationGuide(int id_guide, int id_touriste, Date date_reservation) {
        this.id_guide = id_guide;
        this.id_touriste = id_touriste;
        this.date_reservation = date_reservation;
    }

    public ReservationGuide(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_guide() {
        return id_guide;
    }

    public void setId_guide(int id_guide) {
        this.id_guide = id_guide;
    }

    public int getId_touriste() {
        return id_touriste;
    }

    public void setId_touriste(int id_touriste) {
        this.id_touriste = id_touriste;
    }

    public Date getDate_reservation() {
        return date_reservation;
    }

    public void setDate_reservation(Date date_reservation) {
        this.date_reservation = date_reservation;
    }

    @Override
    public String toString() {
        return "ReservationGuide{" + "id=" + id + ", id_guide=" + id_guide + ", id_touriste=" + id_touriste + ", date_reservation=" + date_reservation + '}';
    }

}
