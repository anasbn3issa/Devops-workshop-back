package com.esprit.examen.services;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.esprit.examen.repositories.ReglementRepository;
import lombok.var;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.Facture;
import com.esprit.examen.entities.Reglement;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ReglementServiceImplTest {
    @Autowired
    IReglementService reglementService;
    @Autowired
    IFactureService factureService;

    @Autowired
    ReglementRepository reglementRepository;

    @BeforeEach
    void setUp() {
        reglementRepository.deleteAll();
    }

    @AfterEach
    void setDown() {
        reglementRepository.deleteAll();
    }

    @Test
    @Order(1)
    public void RetrieveReglements() {
        var reglement1 = new Reglement(500.03F, 200.2F, false, new Date(2020, 11, 2));
        var reglement2 = new Reglement(1000.300F, 0F, true, new Date(2018, 10, 5));
        var reglement3 = new Reglement(0F, 1500.2F, false, new Date(2022, 1, 2));

        reglementRepository.save(reglement1);
        reglementRepository.save(reglement2);
        reglementRepository.save(reglement3);
        var ls = reglementService.retrieveAllReglements();
        Assertions.assertEquals(3, ls.size());
        Assertions.assertEquals(1, ls.get(0).getIdReglement());
        Assertions.assertEquals(200.2F, ls.get(0).getMontantRestant());
    }

    @Test
    @Order(2)
    public void testAddReglement() {
        Reglement r = new Reglement(500.03F, 200.2F, false, new Date(2020, 11, 2));
        Reglement savedReglement = reglementService.addReglement(r);
        assertNotNull(savedReglement.getMontantPaye());
        reglementService.deleteReglement(savedReglement.getIdReglement());
    }

    @Test
    @Order(3)
    public void testAddReglementOptimized() {

        Reglement r = new Reglement(0F, 1500.2F, false, new Date(2022, 1, 2));
        Reglement savedReglement = reglementService.addReglement(r);
        assertNotNull(savedReglement.getIdReglement());
        assertEquals(0F, savedReglement.getMontantPaye(), 0.01);
        assertFalse(savedReglement.getPayee());
        reglementService.deleteReglement(savedReglement.getIdReglement());

    }

    @Test
    @Order(4)
    public void testDeleteReglement() {
        Reglement r = new Reglement(500.03F, 200.2F, false, new Date(2020, 11, 2));
        Reglement savedReglement = reglementService.addReglement(r);
        reglementService.deleteReglement(savedReglement.getIdReglement());
        assertNull(reglementService.retrieveReglement(savedReglement.getIdReglement()));
    }

    @Test
    @Order(5)
    public void GIVEN_2_REGLEMENTS_WHEN_getChiffreAffaireEntreDeuxDate_THEN_EXPECT_1() {
        var reglement1 = new Reglement(1000.300F, 0F, true, new Date(2018, 10, 5));
        var reglement2 = new Reglement(500.03F, 200.2F, false, new Date(2020, 11, 2));
        var f = new Facture();
        f.setIdFacture(4L);
        f.setMontantRemise(5000F);
        f.setMontantFacture(90000F);
        f.setArchivee(false);
        var f2 = new Facture();
        f2.setIdFacture(5L);
        f2.setMontantRemise(1000F);
        f2.setMontantFacture(16000F);
        f2.setArchivee(false);

        Facture newF = factureService.addFacture(f);
        Facture newF2 = factureService.addFacture(f2);
        reglement1.setFacture(newF);
        reglement2.setFacture(newF2);

        reglementRepository.save(reglement1);
        reglementRepository.save(reglement2);

        var res = reglementRepository.getChiffreAffaireEntreDeuxDate(new Date(2017, 10, 5), new Date(2021, 11, 2));
        Assertions.assertEquals(1500.33F, res, 0.01);

    }

}
