package com.esprit.examen;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.repositories.FournisseurRepository;
import com.esprit.examen.services.FournisseurServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FournisseurServiceImplTest {
    @Mock
    private FournisseurRepository fournisseurRepository;

    @InjectMocks
    private FournisseurServiceImpl fournisseurService;


    @BeforeEach
	void
	setUp() {
		fournisseurRepository.deleteAll();
	}

    @Test
    public void testAddFournisseur() {
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setCode("code mock");
        fournisseur.setLibelle("lib mock");
        Mockito.when(fournisseurRepository.save(fournisseur)).thenReturn(fournisseur);
        Fournisseur f = fournisseurService.addFournisseur(fournisseur);
        System.out.println("expected Result"+ fournisseur.getCode());
        org.assertj.core.api.Assertions.assertThat(f.getCode()).isEqualTo("code mock");
        System.out.println("Actual Result"+ f.getCode());
        Mockito.verify(fournisseurRepository).save(fournisseur);
    }

    @Test
    public void testretrieveAllFournisseurs() {
        fournisseurService.retrieveAllFournisseurs();
        Mockito.verify(fournisseurRepository).findAll();
    }

}
