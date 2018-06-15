/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.supermercado.mercadoria;

import controlTest.ResetTable;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import modelo.supermercado.Compra;
import modelo.supermercado.mercadoria.ItemProduto;
import modelo.supermercado.mercadoria.Produto;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author laly_
 */
public class ItemProdutoDAOTest {
    
    public ItemProdutoDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
        @Before
    public void setUp() throws ClassNotFoundException, SQLException {
        ResetTable.cleanAllTables();
        System.out.println("create");
        Produto produto = new Produto("0000", 20.00,"Premium care", "Pampers","Fralda XG", 35.00, 30, 40, "fralda");
        ItemProduto itemProduto = new ItemProduto(35.00,02,produto);
        Compra compra = new Compra(1, new Date(14,06,2018));
        int result = ItemProdutoDAO.create(compra.getId(),itemProduto);
        itemProduto = new ItemProduto(result, itemProduto.getPrecoCompra(),itemProduto.getQuantidade(), itemProduto.getProduto());
        
        System.out.println("id = "+result); 
    }
    
    @After
    public void tearDown() throws ClassNotFoundException, SQLException {
        ResetTable.cleanAllTables();
    }


    /**
     * Test of readItensByCompra method, of class ItemProdutoDAO.
     */
    @Test
    public void testReadItensByCompra() throws Exception {
        System.out.println("readItensByCompra");
        Compra compra = null;
        List<ItemProduto> expResult = null;
        List<ItemProduto> result = ItemProdutoDAO.readItensByCompra(compra);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
