/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import controlTest.ResetTable;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
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
    private Cliente cliente;
    
    public PessoaFisicaDAOTest() {
    }
    
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
        
        Endereco endereco = new Endereco("Jacaraípe", "29177-486", "SERRA", "ES", 75, "Rua Xablau");
        cliente = new Cliente("216.856.707-76", new Date(), 'M', "joel@hotmail.com", "testedesenha", "Joel", endereco);
        
        int result = ClienteDAO.create(cliente);
        
        cliente = new Cliente(cliente.getCpf(), cliente.getDataNasc(), cliente.getGenero(), cliente.getLogin(), cliente.getSenha(),result, cliente.getNome(), endereco);
        System.out.println("id = "+result);
    }
    
    @After
    public void tearDown() throws ClassNotFoundException, SQLException {
        ResetTable.cleanAllTables();
    }

    /**
     * Test of delete method, of class PessoaFisicaDAO.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        int id = cliente.getId();
        PessoaFisicaDAO.delete(id);
    }
    
}
