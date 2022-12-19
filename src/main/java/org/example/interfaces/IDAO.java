package org.example.interfaces;

import org.example.entities.Produit;

import java.util.Date;
import java.util.List;

public interface IDAO<T> {
    void begin();

    boolean create(T o);
    boolean update(T o);
    boolean delete(T o);

    T findById(int id);

    List<T> findAll();

    List<Produit> filterByPrice(double min);

    List<T> filterByDate(Date min, Date max) throws Exception;

    // @Lucie
    List<T> findByMarque(String marque);
    void envoi();

    void close();

}
