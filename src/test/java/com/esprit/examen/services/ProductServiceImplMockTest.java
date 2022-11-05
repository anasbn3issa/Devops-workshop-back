package com.esprit.examen.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.Produit;
import com.esprit.examen.repositories.ProduitRepository;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ProductServiceImplMockTest {

	@Mock
	ProduitRepository produitRepo;
	
	@InjectMocks
	ProduitServiceImpl produitService;
	
	@Test
	@Order(0)
	public void verifierMaxPrix() {
	int max = 20;
	Produit p1 = Produit.builder().libelleProduit("lib").codeProduit("codeprod").prix(10).build();
	System.out.print("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
    Assertions.assertTrue(p1.getPrix()<max);

	
	}
}
