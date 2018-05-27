/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.ResultSet;
import java.util.Date;
import modelo.cliente.Cliente;
import modelo.pessoa.Endereco;
import modelo.pessoa.Pessoa;
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
public class PessoaDAOTest {
    
    public PessoaDAOTest() {
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
     * Test of create method, of class PessoaDAO.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Endereco endereco = new Endereco("Jacaraípe", "29177-486", "SERRA", "ES", 75, "Rua Xablau");
        Pessoa pessoa = new Cliente("785.967.158-98", new Date(), 'M', "joelwb@hotmail.com", "testedesenha", "Joel", endereco);
        int result = PessoaDAO.create(pessoa);
        System.out.println("id = "+result);
    }

    /**
     * Test of delete method, of class PessoaDAO.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        int id = 0;
        PessoaDAO.delete(id);
    }

    /**
     * Test of getEndereco method, of class PessoaDAO.
     */
    @Test
    public void testGetEndereco() throws Exception {
        System.out.println("getEndereco");
        ResultSet rs = null;
        Endereco expResult = null;
        Endereco result = PessoaDAO.getEndereco(rs);
        assertEquals(expResult, result);
    }
    
}
