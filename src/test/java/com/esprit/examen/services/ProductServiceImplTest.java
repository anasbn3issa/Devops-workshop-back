/*
package com.esprit.examen.services;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.DetailFacture;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.repositories.DetailFactureRepository;
import com.esprit.examen.repositories.ProduitRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
	@Autowired
	IProduitService productService;
	
	@Autowired
	ProduitRepository productRepository;
	
	@Autowired
	DetailFactureRepository detailFactureRepository;
	
	//Verifier si le total des produits depasse le maximum
	@Test
	@Order(1)
	public void verifierMaxProducts() {
		//Testing JUnit
		int max=20;
		int total = productRepository.findAll().size();
		Assertions.assertTrue(max>total);
	}
	
	//Un produit est appele demande si sa vente depasse la moitie du total des ventes de tous les produits.
	@Test
	@Order(0)
	public void produitDemande() {
		List <DetailFacture> list = detailFactureRepository.findAll();
		int nbreProduitMax = 0;
		int nbreProduitcourant = 0;
		int i = 0;
        //Le produit le plus vendu
        int totalProduitsVendus = 0;
        
        for (DetailFacture df1 : list) {
        	i=i+1;
            totalProduitsVendus=totalProduitsVendus+1;
            for (DetailFacture df2 : list) {
                if ( (df1.getProduit().getIdProduit())== (df2.getProduit().getIdProduit())) {
                	nbreProduitcourant = nbreProduitcourant + 1;
                }
            }
            if (nbreProduitcourant/2 > nbreProduitMax) {
            	nbreProduitMax = nbreProduitcourant/2;
            }
        }
        Assertions.assertTrue(nbreProduitMax>=totalProduitsVendus/2);
	}
	
}
*/
