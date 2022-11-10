package com.esprit.examen.services;

import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.StockRepository;
import lombok.var;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.HashSet;

@RunWith(MockitoJUnitRunner.class)
//@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class stockServiceImplWithMockTest {
    @Mock
    StockRepository stockRepository;

    @InjectMocks
    StockServiceImpl stockService;

    @BeforeEach
    void
    setUp() {
        stockRepository.deleteAll();
    }

    @AfterEach
    void
    setDown() {
        stockRepository.deleteAll();
    }

    @Test
    public void GIVEN_3_STOCKS_WHEN_retrieveAllStocks_THEN_EXPECT_3(){
        var listStocks = new ArrayList<Stock>();
        var stock1 = new Stock("zeze",12,2);
        var stock2 = new Stock("zeze",12,2);
        var stock3 = new Stock("zeze",12,2);
        listStocks.add(stock1);
        listStocks.add(stock2);
        listStocks.add(stock3);
        Mockito.when(stockRepository.findAll()).thenReturn(listStocks);
        var res = stockService.retrieveAllStocks();
        Assertions.assertEquals(3,res.size());
    }

    @Test
    public void GIVEN_2_PRODUCTS_WHEN_addStock_THEN_EXPECT_stock_added(){
        var p = new Produit();
        p.setIdProduit(1L);
        p.setPrix(5F);
        var p2 = new Produit();
        p2.setIdProduit(2L);
        p2.setPrix(5F);
        var prods = new HashSet<Produit>();
        prods.add(p);
        prods.add(p2);
        var stock1 = new Stock("zeze",12,2);
        stock1.setProduits(prods);
        Mockito.when(stockRepository.save(Mockito.any())).thenReturn(stock1);
        var res = stockService.addStock(stock1);
        Assertions.assertEquals(2,res.getProduits().size());
        Assertions.assertTrue(res.getProduits().contains(p));
        Assertions.assertTrue(res.getProduits().contains(p2));
        Assertions.assertTrue(res.getQte()>res.getQteMin());
    }
}
