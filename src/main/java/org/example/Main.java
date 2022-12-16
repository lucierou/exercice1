package org.example;

import org.example.entities.Produit;

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
        p1.setPrix(500.0);
        p1.setDateAchat(new Date("2020/10/10"));
        em.flush();
        transaction.commit();

        Produit p4 = em.find(Produit.class, 1);
        System.out.println("Après modification : " + p4);



        System.out.println("2.1 : Tous les produits : ");
        List<Produit> results = em.createNativeQuery("SELECT * FROM produit", Produit.class).getResultList();
        for (Produit p : results) {
            System.out.println(p);
        }



        System.out.println("2.2 : Produits dont le prix est supérieur à 100 : ");
        Query query22 = em.createQuery("SELECT p FROM Produit p WHERE p.prix > 100");
        List<Produit> results22 = query22.getResultList();
        for (Produit p : results22) {
            System.out.println(p);
        }

        System.out.println("2.3 : Produits achetés entre le 2022-12-10 et le 2022-12-18: ");
        Query query23 = em.createQuery("SELECT p FROM Produit p WHERE p.dateAchat > '2022-12-10' AND p.dateAchat < '2022-12-18'");
        List<Produit> results23 = query23.getResultList();
        for (Produit p : results23) {
            System.out.println(p);
        }


        em.close();
        emf.close();
    }
}