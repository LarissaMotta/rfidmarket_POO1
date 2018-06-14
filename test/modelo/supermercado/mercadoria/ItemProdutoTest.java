/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.supermercado.mercadoria;

import controlTest.ResetTable;
import database.supermercado.mercadoria.ProdutoDAO;
import java.sql.SQLException;
import modelo.supermercado.Supermercado;
import modelo.usuarios.Endereco;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 20162bsi0040
 */
public class ItemProdutoTest {
    private ItemProduto itemProduto;
    
    public ItemProdutoTest() {
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
        Endereco endereco = new Endereco("Jacaraípe", "29177-486", "SERRA", Endereco.Estado.ES, 75, "Rua Xablau");
        Supermercado supermercado = new Supermercado(-52.2471,-2.5297,"serra 03","44.122.623/0001-02", "EPA", endereco);
        Produto produto = new Produto("0000", 20.00,"Premium care", "Pampers","Fralda XG", 35.00, 30, 40, "fralda");
        itemProduto = new ItemProduto(35.00,02,produto);
        
        //create(int idCompra, ItemProduto item)
        //int result = ItemProdutoDAO.create( idCompra,supermercado);
        
        //int id, double precoCompra, int quantidade, Produto produto)
        itemProduto = new ItemProduto(result, itemProduto.getPrecoCompra(),itemProduto.getQuantidade(), itemProduto.getProduto());
        
        System.out.println("id = "+result);
       
        
    }
    
    @After
    public void tearDown() throws ClassNotFoundException, SQLException {
        ResetTable.cleanAllTables();
    }

    /**
     * Test of getId method, of class ItemProduto.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        ItemProduto instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPrecoCompra method, of class ItemProduto.
     */
    @Test
    public void testGetPrecoCompra() {
        System.out.println("getPrecoCompra");
        ItemProduto instance = null;
        double expResult = 0.0;
        double result = instance.getPrecoCompra();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuantidade method, of class ItemProduto.
     */
    @Test
    public void testGetQuantidade() {
        System.out.println("getQuantidade");
        ItemProduto instance = null;
        int expResult = 0;
        int result = instance.getQuantidade();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProduto method, of class ItemProduto.
     */
    @Test
    public void testGetProduto() {
        System.out.println("getProduto");
        ItemProduto instance = null;
        Produto expResult = null;
        Produto result = instance.getProduto();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
