/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.Date;
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
public class PessoaFisicaDAOTest {
    
    public PessoaFisicaDAOTest() {
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
     * Test of create method, of class PessoaFisicaDAO.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Endereco endereco = new Endereco("Jacaraípe", "29177-486", "SERRA", "ES", 75, "Rua Xablau");
        PessoaFisica pessoaFisica = new Cliente("216.856.707-76", new Date(), 'M', "joel@hotmail.com", "testedesenha", "Joel", endereco);
        int result = PessoaFisicaDAO.create(pessoaFisica);
        System.out.println("id = "+result);
    }
    
}
