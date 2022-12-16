package org.example;

import org.example.entities.Produit;
import org.example.services.ProduitService;

import java.util.Date;

public class MainCorrection {
    public static void main(String[] args) {
        System.out.println("******** Exercice 1 ********");

        // ** 1 ** Création de 5 produits
        ProduitService ps = new ProduitService();
        ps.begin();
        ps.create(new Produit("TOSHIBA", "sdfsef", new Date("2015/01/08"), 100.0, 12));
        ps.create(new Produit("HP", "sdgfsdesef", new Date("2015/01/09"), 200.0, 12));
        ps.create(new Produit("SONY", "sdjhusef", new Date("2015/01/10"), 300.0, 12));
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

        ps.close();







    }
}
