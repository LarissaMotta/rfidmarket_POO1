/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.SQLException;
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
    private PessoaJuridica pessoaJuridica;
    
    public PessoaJuridicaDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws ClassNotFoundException, SQLException {
        System.out.println("create");
        
        Endereco endereco = new Endereco("Jacaraípe", "29177-486", "SERRA", "ES", 75, "Rua Xablau");
        pessoaJuridica = new Fornecedor("44.122.623/0001-02", "Coca-Cola", endereco);
        int result = PessoaJuridicaDAO.create(pessoaJuridica);
        
        pessoaJuridica = new Fornecedor(pessoaJuridica.getCnpj(),result, pessoaJuridica.getNome(), endereco);
        
        System.out.println("id = "+result);
    }
    
    @After
    public void tearDown() {
    }
    /**
     * Test of delete method, of class PessoaJuridicaDAO.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        int id = pessoaJuridica.getId();
        PessoaJuridicaDAO.delete(id);
    }
    
}
