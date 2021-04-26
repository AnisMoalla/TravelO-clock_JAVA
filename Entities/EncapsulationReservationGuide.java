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
public class EncapsulationReservationGuide {
    private static int id;
    private static int id_guide;
    private static int id_touriste;
    private static Date date_reservation;

    public EncapsulationReservationGuide()  {
    }

    public EncapsulationReservationGuide(int id, int id_guide, int id_touriste, Date date_reservation) {
        this.id = id;
        this.id_guide = id_guide;
        this.id_touriste = id_touriste;
        this.date_reservation = date_reservation;
    }

    public EncapsulationReservationGuide(int id_guide, int id_touriste, Date date_reservation) {
        this.id_guide = id_guide;
        this.id_touriste = id_touriste;
        this.date_reservation = date_reservation;
    }

    public EncapsulationReservationGuide(int id) {
        this.id = id;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        EncapsulationReservationGuide.id = id;
    }

    public static int getId_guide() {
        return id_guide;
    }

    public static void setId_guide(int id_guide) {
        EncapsulationReservationGuide.id_guide = id_guide;
    }

    public static int getId_touriste() {
        return id_touriste;
    }

    public static void setId_touriste(int id_touriste) {
        EncapsulationReservationGuide.id_touriste = id_touriste;
    }

    public static Date getDate_reservation() {
        return date_reservation;
    }

    public static void setDate_reservation(Date date_reservation) {
        EncapsulationReservationGuide.date_reservation = date_reservation;
    }

    @Override
    public String toString() {
        return "ReservationGuide{" + "id=" + id + ", id_guide=" + id_guide + ", id_touriste=" + id_touriste + ", date_reservation=" + date_reservation + '}';
    }

}
