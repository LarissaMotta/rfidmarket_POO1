package database;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Date;
import modelo.cliente.Cartao;
import modelo.cliente.Cliente;
import modelo.pessoa.Endereco;
import modelo.pessoa.PessoaFisica;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joel-
 */
public class ClienteDAOTest {
    
    public ClienteDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class ClienteDAO.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        
        Endereco endereco = new Endereco("Jacaraípe", "29177-486", "SERRA", "ES", 75, "Rua Xablau");
        Cliente cliente = new Cliente("216.856.707-76", new Date(), 'M', "joel@hotmail.com", "testedesenha", "Joel", endereco);
        int result = ClienteDAO.create(cliente);
        System.out.println("id = "+result);
    }

    /**
     * Test of addCartao method, of class ClienteDAO.
     */
    /*@Test
    public void testAddCartao() throws Exception {
        System.out.println("addCartao");
        Cliente cliente = null;
        Cartao cartao = null;
        int expResult = 0;
        int result = ClienteDAO.addCartao(cliente, cartao);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    
}
