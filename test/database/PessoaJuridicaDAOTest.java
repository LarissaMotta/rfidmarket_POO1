/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import modelo.pessoa.Endereco;
import modelo.pessoa.PessoaJuridica;
import modelo.supermercado.mercadoria.Fornecedor;
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
public class PessoaJuridicaDAOTest {
    
    public PessoaJuridicaDAOTest() {
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
     * Test of create method, of class PessoaJuridicaDAO.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Endereco endereco = new Endereco("Jacaraípe", "29177-486", "SERRA", "ES", 75, "Rua Xablau");
        PessoaJuridica pessoaJuridica = new Fornecedor("44.122.623/0001-02", "Coca-Cola", endereco);
        int result = PessoaJuridicaDAO.create(pessoaJuridica);
        System.out.println("id = "+result);
    }
    
}
