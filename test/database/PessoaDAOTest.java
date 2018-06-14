/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import controlTest.ResetTable;
import static database.core.CoreDAO.getConnection;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import database.usuarios.ClienteDAO;
import database.usuarios.PessoaDAO;
import database.usuarios.PessoaFisicaDAO;
import modelo.usuarios.Cliente;
import modelo.usuarios.Endereco;
import modelo.usuarios.PessoaFisica;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author joel-
 */
public class PessoaDAOTest {
    private Cliente cliente;
    
    public PessoaDAOTest() {
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
        
        Endereco endereco = new Endereco("Jacaraípe", "29177-486", "SERRA", Endereco.Estado.ES, 75, "Rua Xablau");
        cliente = new Cliente("216.856.707-76", new Date(), PessoaFisica.Genero.M, "joel@hotmail.com", "testedesenha", "Joel", endereco);
        
        int result = ClienteDAO.create(cliente);
        
        cliente = new Cliente(cliente.getCpf(), cliente.getDataNasc(), cliente.getGenero(), cliente.getLogin(), cliente.getSenha(),result, cliente.getNome(), endereco);
        System.out.println("id = "+result);
    }
    
    @After
    public void tearDown() throws ClassNotFoundException, SQLException {
        ResetTable.cleanAllTables();
    }

    /**
     * Test of delete method, of class PessoaDAO.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        int id = cliente.getId();
        PessoaFisicaDAO.delete(id);
        PessoaDAO.delete(id);
    }

    /**
     * Test of getEndereco method, of class PessoaDAO.
     */
    @Test
    public void testGetEndereco() throws Exception {
        System.out.println("getEndereco");
        Connection conexao = getConnection();

        // Forme a string sql;
        String sql = "SELECT numero, rua, cep, bairro, estado, cidade FROM usuarios";
        
        // Substitua a '?' pelo valor da coluna;
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.executeQuery();
        ResultSet rs = ps.getResultSet();
        rs.next();
        
        Endereco expResult = new Endereco("Jacaraípe", "29177-486", "SERRA", Endereco.Estado.ES, 75, "Rua Xablau");
        Endereco result = PessoaDAO.getEndereco(rs);
    }
    
}
