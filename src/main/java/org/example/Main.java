package org.example;

import org.example.model.Produit;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercice1");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        Produit produit;
        for (int i=0; i<5; i++) {
            produit = new Produit();
            produit.setMarque("marque" + (i+1));
            produit.setReference("reference" + (i+1));
            produit.setDateAchat(new Date());
            produit.setPrix(25.0);
            produit.setStock(i);

            em.persist(produit);
            transaction.commit();
            transaction.begin();
            produit = null;
        }

        System.out.println("1 : Liste des produits : ");
        Query query1 = em.createQuery("select p from Produit p");
        List produits = query1.getResultList();
        for (Object prod : produits) {
            Produit tmp = (Produit) prod;
            System.out.println(tmp);
        }



        System.out.println("2 : Affichage produit avec id = 2 : ");
        Produit p2 = em.find(Produit.class, 2);
        System.out.println(p2);
        transaction.commit();



        System.out.println("3: Suppression du produit avec id = 3 :");
        transaction.begin();
        Produit p3 = em.find(Produit.class, 3);
        em.remove(p3);
        transaction.commit();
        Query query3 = em.createQuery("select p from Produit p");
        List prods = query3.getResultList();
        for (Object prod : prods) {
            Produit tmp = (Produit) prod;
            System.out.println(tmp);
        }



        System.out.println("4: Modification du produit avec id = 1 :");
        transaction.begin();

        Produit p1 = em.find(Produit.class, 1);
        System.out.println("Produit avec id = 1 (avant modif) : " + p1);
        p1.setMarque("marque modifiée");
        p1.setReference("référence modifiée");
        em.flush();
        transaction.commit();

        Produit p4 = em.find(Produit.class, 1);
        System.out.println("Après modification : " + p4);

    }
}