package interfaces;

import entites.Reclamation;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface IService<T>{
    public void add(T entity) throws SQLException;
    public ObservableList getAll();
    public void modify(int id , String e) throws SQLException;
    public void modifyProm(T entity) throws SQLException;
    public void delete(int id ) throws SQLException;
    public Reclamation findByid(int id) throws SQLException;

    ObservableList getAll_Byid(int Id);
}
