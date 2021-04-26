package Interfaces;

import java.util.List;

public interface IService<T> {

    public void add(T entity) ;
    public List<T> getAll() ;
    public void delete(int id) ;
    public void Modify(T entity) ;
    public T getEntity(int id) ;
}
