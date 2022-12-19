package org.example;

import org.example.entities.Produit;
import org.example.services.ProduitService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainCorrection {
    public static void main(String[] args) throws Exception {
        System.out.println("******** Exercice 1 ********");

        // ** 1 ** Création de 5 produits
        ProduitService ps = new ProduitService();
        ps.begin();
        ps.create(new Produit("TOSHIBA", "sdfsef", new Date("2015/01/08"), 100.0, 12));
        ps.create(new Produit("HP", "sdgfsdesef", new Date("2016/01/09"), 200.0, 12));
        ps.create(new Produit("SONY", "sdjhusef", new Date("2016/01/10"), 300.0, 12));
        ps.create(new Produit("DELL", "sdftrertef", new Date("2015/01/11"), 400.0, 12));
        ps.create(new Produit("ASUS", "sdfazazaaf", new Date("2015/01/12"), 500.0, 12));
        ps.envoi();

        // ** 2 ** Information produit id = 2
        ps.begin();
        Produit p = ps.findById(2);
        System.out.println(p);
        ps.envoi();

        // ** 3 ** Suppression produit id = 3
        ps.begin();
        ps.delete(ps.findById(3));
        ps.envoi();

        // ** 4 ** Modification des infos du produit dont id = 1
        ps.begin();
        p = ps.findById(1);
        if (p != null) {
            p.setMarque("HP");
            p.setReference("HPHPHPHP");
            p.setDateAchat(new Date("2010/10/10"));
            p.setPrix(5000.0);
            ps.update(p);
        }
        ps.envoi();



        // ** 2.1 ** Afficher tous les produits
        System.out.println("Tous les produits");
        ps.begin();
        for(Produit produit : ps.findAll()){
            System.out.println(produit);
        }
        ps.envoi();

        // ** 2.2 ** Afficher tous les produits au prix supérieur à 100
        System.out.println("Tous les produits au dessus de 100 euros : ");
        ps.begin();
        for(Produit produitCher : ps.filterByPrice(100)){
            System.out.println(produitCher);
        }
        ps.envoi();

        // ** 2.3 ** Afficher tous les produits achetés entre le 2016-01-01 et le 2016-12-31
        System.out.println("2.3 : Produits achetés entre le 2016-01-01 et le 2016-12-31: ");
        ps.begin();
        String madate1 = "2016/01/01";
        String madate2 = "2016/12/31";
//        Date date1 = new SimpleDateFormat("yyyy/MM/dd").parse(madate1);
//        Date date2 = new SimpleDateFormat("yyyy/MM/dd").parse(madate2);
        Date date1 = new Date(madate1);
        Date date2 = new Date(madate2);

        List<Produit> produitsDate = ps.filterByDate(date1, date2);
        for (Produit prod : produitsDate) {
            System.out.println(prod);
        }
        ps.envoi();


        // @Lucie
        // ** 3.1 ** Afficher la liste des produits commandés entre deux dates lues au clavier
        System.out.println("3.1 : Produits achetés entre deux dates lues au clavier : ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Saisir la date de début yyyy/MM/dd : ");
        String dateDebut = scanner.next();
        System.out.println(dateDebut);
        System.out.println("Saisir la date de fin yyyy/MM/dd : ");
        String dateFin = scanner.next();
        System.out.println(dateFin);
        Date date3 = new Date(dateDebut);
        Date date4 = new Date(dateFin);

        ps.begin();
        List<Produit> produitsDate2 = ps.filterByDate(date3, date4);
        for (Produit prod : produitsDate2) {
            System.out.println(prod);
        }
        ps.envoi();

        // @Lucie
        // ** 3.2 ** Retourner les valeurs des articles d'une même marque lue au clavier
        System.out.println("3.2 : Produits d'une même marque lue au clavier : ");
        System.out.println("Saisir une marque : ");
        String marqueSaisie = scanner.next();

        ps.begin();
        List<Produit> produitsParMarque = ps.findByMarque(marqueSaisie);
        if (produitsParMarque != null) {
            for (Produit prod : produitsParMarque) {
                System.out.println(prod);
            }
        }
        ps.envoi();









        ps.close();







    }
}
