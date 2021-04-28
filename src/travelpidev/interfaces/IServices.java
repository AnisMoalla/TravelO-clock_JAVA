/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelpidev.interfaces;

import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author Wofurani
 */
public interface IServices <T> {
       public void add(T entity);
    
    public ObservableList<T> getAll();
    public void delete(T entity);
    public void edit(T entity);
}
