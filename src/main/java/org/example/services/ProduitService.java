package org.example.services;

import org.example.entities.Produit;
import org.example.interfaces.IDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public class ProduitService implements IDAO<Produit> {

    private EntityManagerFactory emf;

    private EntityManager em;

    public ProduitService() {
        emf = Persistence.createEntityManagerFactory("exercice1");
        em = emf.createEntityManager();
    }
    @Override
    public void begin() {
        em.getTransaction().begin();
        System.out.println("Démarrage de la persistance");
    }

    @Override
    public boolean create(Produit o) {
        em.persist(o);
        return true;
    }

    @Override
    public boolean update(Produit o) {
        em.persist(o);
        return true;
    }

    @Override
    public boolean delete(Produit o) {
        em.remove(o);
        return true;
    }

    @Override
    public Produit findById(int id) {
        return em.find(Produit.class, id);
    }

    @Override
    public List<Produit> findAll() {
        Query query = em.createQuery("SELECT p FROM Produit p");
        List<Produit> list = query.getResultList();
        return list;
    }

    public List<Produit> filterByPrice(double min){
        Query query = em.createQuery("select p from Produit p where p.prix >= :min");
        query.setParameter("min",min);
        List<Produit> list = query.getResultList();
        return list;
    }

    public List<Produit> filterByDate(Date min, Date max) throws Exception {
        if (min.before(max)) {
            Query query = em.createQuery("SELECT p FROM Produit p WHERE p.dateAchat BETWEEN :dateDebut AND :dateFin");
            query.setParameter("dateDebut", min);
            query.setParameter("dateFin", max);
            List<Produit> results = query.getResultList();
            return results;
        }
        throw new Exception("error date");
    }

    // @Lucie
    @Override
    public List<Produit> findByMarque(String marque) {
        marque = marque.toUpperCase();
        Query query = em.createQuery("SELECT p FROM Produit p WHERE p.marque = :marque");
        query.setParameter("marque", marque);
        List<Produit> results = query.getResultList();
        if (!results.isEmpty()) {
            return results;
        } else {
            System.out.println("Pas de produit de cette marque");
        }
        return null;
    }

    @Override
    public void envoi() {
        em.getTransaction().commit();
    }

    @Override
    public void close() {
        em.close();
    }





}
