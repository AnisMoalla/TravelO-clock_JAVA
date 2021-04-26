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
public interface IVendeur<T> {
    public void ajouterVendeur(T t);
    public void supprimerVendeur(T t);
    public void modifierVendeur(T t);
    public List<T> vendeursList(); 
}
