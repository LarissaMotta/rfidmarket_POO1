/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.supermercado;

import controlTest.ResetTable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.pagamento.Cartao;
import modelo.supermercado.Compra;
import modelo.supermercado.Supermercado;
import modelo.supermercado.mercadoria.ItemProduto;
import modelo.supermercado.mercadoria.Produto;
import modelo.usuarios.Cliente;
import modelo.usuarios.Endereco;
import objGeneretor.*;
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
public class CompraDAOTest {
    private Compra compra;
    public CompraDAOTest() {
    }
    
    //create(Compra compra, Cliente cliente, Cartao cartao, Supermercado supermercado)
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws ClassNotFoundException, SQLException, UnsupportedEncodingException, NoSuchAlgorithmException {
        ResetTable.cleanAllTables();
        System.out.println("create");
        Endereco endereco = new Endereco("Jacaraípe", "29177-486", "SERRA", Endereco.Estado.ES, 75, "Rua Xablau");
        Supermercado supermercado = new Supermercado(1,-52.2471,-2.5297,"serra 03","44.122.623/0001-02", "EPA", endereco);
        
        List<ItemProduto> itens = new ArrayList<>();
        Cliente cliente = ClienteTDAO.readCliente();
        Cartao cartao = CartaoTDAO.readCartao();
        Produto produto = new Produto("0000", 20.00,"Premium care", "Pampers","Fralda XG", 35.00, 30, 40, "fralda");
        ItemProduto itemProduto = new ItemProduto(35.00,02,produto);
        itens.add(itemProduto);
        compra = new Compra(new Date(2018,06,20),itens);
        int result = CompraDAO.create(compra,cliente,cartao,supermercado);
        compra = new Compra(result, compra.getDataHora());
        
        System.out.println("id = "+result);
    }
    @After
    public void tearDown() throws ClassNotFoundException, SQLException {
        ResetTable.cleanAllTables();
    }



    /**
     * Test of readHistoricoCompras method, of class CompraDAO.
     */
    @Test
    public void testReadHistoricoCompras() throws Exception {
        System.out.println("readHistoricoCompras");
        Cliente cliente = null;
        List<Compra> expResult = null;
        List<Compra> result = CompraDAO.readHistoricoCompras(cliente);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readHistoricoComprasByCartao method, of class CompraDAO.
     */
    @Test
    public void testReadHistoricoComprasByCartao() throws Exception {
        System.out.println("readHistoricoComprasByCartao");
        Cliente cliente = null;
        Cartao cartao = null;
        List<Compra> expResult = null;
        List<Compra> result = CompraDAO.readHistoricoComprasByCartao(cliente, cartao);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readHistoricoComprasBySupermercado method, of class CompraDAO.
     */
    @Test
    public void testReadHistoricoComprasBySupermercado() throws Exception {
        System.out.println("readHistoricoComprasBySupermercado");
        Supermercado supermercado = null;
        List<Compra> expResult = null;
        List<Compra> result = CompraDAO.readHistoricoComprasBySupermercado(supermercado);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
