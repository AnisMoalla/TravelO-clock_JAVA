/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oussema.Interfaces;

import java.util.List;

/**
 *
 * @author masso
 */
public interface IReservationGuide<T> {
    public void ajouterReservationGuide(T t);
    public void supprimerReservationGuide(T t);
    public void modifierReservationGuide(T t);
    public List<T> reservationGuidesList();  
}
