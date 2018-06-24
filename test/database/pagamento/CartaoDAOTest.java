/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.pagamento;

import controlTest.ResetTable;
import database.usuarios.ClienteDAO;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import modelo.pagamento.Cartao;
import modelo.usuarios.Cliente;
import modelo.usuarios.Endereco;
import modelo.usuarios.PessoaFisica;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 20162bsi0147
 */
public class CartaoDAOTest {
     private Cartao cartao;
    
    public CartaoDAOTest() {
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
        
        cartao = new Cartao("MasterCard", new  java.util.Date(2019, 8, 1), "5482657412589634", "Maria", Cartao.Tipo.CREDITO);
        int result = CartaoDAO.create(cartao);   
        cartao = new Cartao(result, cartao.getBandeira(), cartao.getDataValid(), cartao.getNumero(), cartao.getTitular(), cartao.getTipo());
        
        System.out.println("id = "+result);
    }
    
    ///LALALA
    
    @After
    public void tearDown() throws ClassNotFoundException, SQLException {
        ResetTable.cleanAllTables();
    }

    /**
     * Test of readCartoesByCliente method, of class CartaoDAO.
     */
    @Test
    public void testReadCartoesByCliente() throws Exception {
        System.out.println("readCartoesByCliente");
        
        Endereco end = new Endereco("Jacaraípe", "29177-486", "SERRA", Endereco.Estado.ES, 75, "Rua Xablau");
        Cliente cliente = new Cliente("216.856.707-76", new Date(), PessoaFisica.Genero.M, "joel@hotmail.com", "testedesenha",02, "Joel", end);
        int idCliente = ClienteDAO.create(cliente);
        cliente = new Cliente(cliente.getCpf(), cliente.getDataNasc(), cliente.getGenero(), cliente.getLogin(), cliente.getSenha(),idCliente, cliente.getNome(), end);
        
        ClienteDAO.addCartao(cliente, cartao);

        
        List<Cartao> result = CartaoDAO.readCartoesByCliente(cliente);
        System.out.println(result);
    }
    
    /**
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
   

  

  
        

}
