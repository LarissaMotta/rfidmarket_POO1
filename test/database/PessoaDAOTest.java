/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

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
        Pessoa pessoa = new Cliente("785.967.158-98", new Date(), 'M', "joelwb@hotmail.com", "3589745", "testedesenha", "Joel", endereco);
        int result = PessoaDAO.create(pessoa);
        System.out.println("id = "+result);
    }
    
}
