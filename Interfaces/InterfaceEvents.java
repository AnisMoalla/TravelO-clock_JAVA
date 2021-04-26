/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEvents.Interfaces;

import java.util.List;

/**
 *
 * @author legion
 */
public interface InterfaceEvents<E> {
    public void ajouterEvent(E e);
    public void supprimerEvent(E e);
    public void modifierEvent(E e1,E e2);
    public List<E> afficherEvent(); 
    public double recupererPrixEvent(int id);
    public void modifierNbrPlace(int id);
    public E getEventById(int id);
    public void voter(E e);
}
