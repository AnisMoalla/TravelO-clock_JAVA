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
public interface IGuide<T> {
    public void ajouterGuide(T t);
    public void supprimerGuide(T t);
    public void modifierGuide(T t);
    public List<T> guidesList(); 
}
