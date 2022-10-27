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

/*@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
	@Autowired
	IProduitService productService;
	
	@Autowired
	ProduitRepository productRepository;
	
	@Autowired
	DetailFactureRepository detailFactureRepository;
	
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
	public void produitDemande(int max) {
		List <DetailFacture> list = detailFactureRepository.findAll();
		int nbreProduitMax = 0;
		int nbreProduitcourant = 0;
        Produit prodMax = new Produit();
        //Le produit le plus vendu
        int totalProduits = productRepository.findAll().size() ;
        int totalProduitsVendus = 0;
        
        for (Produit prod : productRepository.findAll()) {

            for (DetailFacture df : list) {
                if (df.getProduit().equals(prod)) {
                	nbreProduitcourant = nbreProduitcourant + 1;
                }
               totalProduitsVendus=totalProduitsVendus+1;
            }
            if (nbreProduitcourant > nbreProduitMax) {
            	nbreProduitMax = nbreProduitcourant;
            	prodMax = prod;
            }
        }
        Assertions.assertTrue(nbreProduitMax>totalProduitsVendus/2);
        
	}
	
}*/
